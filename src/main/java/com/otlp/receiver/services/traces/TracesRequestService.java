package com.otlp.receiver.services.traces;

import com.otlp.receiver.services.Result;
import com.otlp.receiver.utils.Exceptions;
import io.opentelemetry.proto.collector.trace.v1.ExportTracePartialSuccess;
import io.opentelemetry.proto.collector.trace.v1.ExportTraceServiceRequest;
import io.opentelemetry.proto.collector.trace.v1.ExportTraceServiceResponse;
import io.opentelemetry.proto.trace.v1.ResourceSpans;

import java.util.List;
import java.util.StringJoiner;

public class TracesRequestService {
    private static void persistResourceSpanData(io.opentelemetry.proto.trace.v1.ResourceSpans resourceSpanM) throws Exceptions.ValidationError, Exceptions.ValidationWarning {
        for (io.opentelemetry.proto.trace.v1.ScopeSpans scopeSpanM : resourceSpanM.getScopeSpansList()) {
            for (io.opentelemetry.proto.trace.v1.Span spanM : scopeSpanM.getSpansList()) {
                SpanService.persistSpan(spanM, scopeSpanM, resourceSpanM);
            }
        }
    }

    private static Result processResourceSpan(io.opentelemetry.proto.trace.v1.ResourceSpans resourceSpanM) {
        try {
            persistResourceSpanData(resourceSpanM);
        } catch (Exceptions.ValidationError error) {
            return new Result(Result.Type.FAILURE, error.getMessage());
        } catch (Exceptions.ValidationWarning warning) {
            return new Result(Result.Type.WARNING, warning.getMessage());
        }

        return new Result(Result.Type.SUCCESS, "");
    }

    private static ExportTracePartialSuccess buildPartialSuccess(long rejectedItems, String errorMessage) {
        ExportTracePartialSuccess partialSuccess = null;
        if (rejectedItems != 0 || !errorMessage.isEmpty()) {
            partialSuccess = ExportTracePartialSuccess.newBuilder()
                    .setRejectedSpans(rejectedItems)
                    .setErrorMessage(errorMessage)
                    .build();
        }
        return partialSuccess;
    }

    public static ExportTraceServiceResponse processTraceRequest(ExportTraceServiceRequest request) throws Exceptions.InvalidArguments {
        long rejectedItems = 0;
        StringJoiner errorMessageJoiner = new StringJoiner("\n");
        List<ResourceSpans> resourceSpans = request.getResourceSpansList();
        for (ResourceSpans resourceSpan : resourceSpans) {
            Result result = processResourceSpan(resourceSpan);
            if (result.getType() == Result.Type.FAILURE)
                rejectedItems += 1;
            errorMessageJoiner.add(result.getMessage());
        }
        if (rejectedItems == resourceSpans.size())
            throw new Exceptions.InvalidArguments(errorMessageJoiner.toString());

        ExportTracePartialSuccess partialSuccess = buildPartialSuccess(rejectedItems, errorMessageJoiner.toString());
        ExportTraceServiceResponse.Builder responseBuilder = ExportTraceServiceResponse.newBuilder();
        if (partialSuccess == null)
            return responseBuilder.clearPartialSuccess().build();
        return responseBuilder.setPartialSuccess(partialSuccess).build();
    }
}
