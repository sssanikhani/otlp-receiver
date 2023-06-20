package com.otlp.receiver.services.logs;

import com.otlp.receiver.services.Result;
import com.otlp.receiver.utils.Exceptions;
import io.opentelemetry.proto.collector.logs.v1.ExportLogsPartialSuccess;
import io.opentelemetry.proto.collector.logs.v1.ExportLogsServiceRequest;
import io.opentelemetry.proto.collector.logs.v1.ExportLogsServiceResponse;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.StringJoiner;

public class LogService {
    private static void validateResourceLog(io.opentelemetry.proto.logs.v1.ResourceLogs resourceLog)
            throws Exceptions.ValidationError, Exceptions.ValidationWarning {
        // TODO: validate data
    }

    private static void persistResourceLogData(io.opentelemetry.proto.logs.v1.ResourceLogs resourceLogM) {
        for (io.opentelemetry.proto.logs.v1.ScopeLogs scopeLogM : resourceLogM.getScopeLogsList()) {
            for (io.opentelemetry.proto.logs.v1.LogRecord logRecordM : scopeLogM.getLogRecordsList()) {
                LogRecordService.persistLogRecord(logRecordM, scopeLogM, resourceLogM);
            }
        }
    }

    @Contract("_ -> new")
    private static @NotNull Result processResourceLog(io.opentelemetry.proto.logs.v1.ResourceLogs resourceLog) {
        try {
            validateResourceLog(resourceLog);
        } catch (Exceptions.ValidationError error) {
            return new Result(Result.Type.FAILURE, error.getMessage());
        } catch (Exceptions.ValidationWarning warning) {
            persistResourceLogData(resourceLog);
            return new Result(Result.Type.WARNING, warning.getMessage());
        }

        persistResourceLogData(resourceLog);
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
