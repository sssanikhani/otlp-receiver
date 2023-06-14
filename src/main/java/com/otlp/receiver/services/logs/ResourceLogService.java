package com.otlp.receiver.services.logs;

import com.otlp.receiver.models.logs.ResourceLog;
import com.otlp.receiver.models.resources.Resource;
import com.otlp.receiver.services.ResourceService;
import com.otlp.receiver.services.Result;
import com.otlp.receiver.utils.Exceptions;
import com.otlp.receiver.utils.ObjectPersister;
import io.opentelemetry.proto.collector.logs.v1.ExportLogsPartialSuccess;
import io.opentelemetry.proto.collector.logs.v1.ExportLogsServiceRequest;
import io.opentelemetry.proto.collector.logs.v1.ExportLogsServiceResponse;
import io.opentelemetry.proto.logs.v1.ResourceLogs;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.StringJoiner;

public class ResourceLogService {
    private static void validateResourceLog(io.opentelemetry.proto.logs.v1.ResourceLogs resourceLog)
            throws Exceptions.ValidationError, Exceptions.ValidationWarning {
        return; // TODO: validate data
    }

    private static void persistScopeLogs(List<io.opentelemetry.proto.logs.v1.ScopeLogs> scopeLogs, ResourceLog resourceLog) {
        for (io.opentelemetry.proto.logs.v1.ScopeLogs scopeLog : scopeLogs) {
            ScopeLogService.persistScopeLog(scopeLog, resourceLog);
        }
    }

    private static void persistResourceLog(ResourceLogs resourceLogM) {
        Resource resource = ResourceService.persistResource(resourceLogM.getResource());
        ResourceLog resourceLog = new ResourceLog(resource, resourceLogM.getSchemaUrl());
        ObjectPersister.persist(resourceLog);
        persistScopeLogs(resourceLogM.getScopeLogsList(), resourceLog);
    }

    @Contract("_ -> new")
    private static @NotNull Result processResourceLog(io.opentelemetry.proto.logs.v1.ResourceLogs resourceLog) {
        try {
            validateResourceLog(resourceLog);
        } catch (Exceptions.ValidationError error) {
            return new Result(Result.Type.FAILURE, error.getMessage());
        } catch (Exceptions.ValidationWarning warning) {
            persistResourceLog(resourceLog);
            return new Result(Result.Type.WARNING, warning.getMessage());
        }

        persistResourceLog(resourceLog);
        return new Result(Result.Type.SUCCESS, "");
    }

    private static ExportLogsPartialSuccess buildPartialSuccess(long rejectedItems, String errorMessage) {
        ExportLogsPartialSuccess partialSuccess = null;
        if (rejectedItems != 0 || !errorMessage.isEmpty()) {
            partialSuccess = ExportLogsPartialSuccess.newBuilder()
                    .setRejectedLogRecords(rejectedItems)
                    .setErrorMessage(errorMessage)
                    .build();
        }
        return partialSuccess;
    }

    public static @NotNull ExportLogsServiceResponse processLogsRequest(@NotNull ExportLogsServiceRequest request)
            throws Exceptions.InvalidArguments {
        long rejectedItems = 0;
        StringJoiner errorMessageJoiner = new StringJoiner("\n");
        List<io.opentelemetry.proto.logs.v1.ResourceLogs> resourceLogs = request.getResourceLogsList();
        for (io.opentelemetry.proto.logs.v1.ResourceLogs resourceLog : resourceLogs) {
            Result result = processResourceLog(resourceLog);
            if (result.getType() == Result.Type.FAILURE)
                rejectedItems += 1;
            errorMessageJoiner.add(result.getMessage());
        }
        if (rejectedItems == resourceLogs.size())
            throw new Exceptions.InvalidArguments(errorMessageJoiner.toString());

        ExportLogsPartialSuccess partialSuccess = buildPartialSuccess(rejectedItems, errorMessageJoiner.toString());
        ExportLogsServiceResponse.Builder responseBuilder = ExportLogsServiceResponse.newBuilder();
        if (partialSuccess == null)
            return responseBuilder.clearPartialSuccess().build();
        return responseBuilder.setPartialSuccess(partialSuccess).build();
    }
}
