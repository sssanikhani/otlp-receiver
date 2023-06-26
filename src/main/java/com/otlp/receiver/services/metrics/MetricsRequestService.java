package com.otlp.receiver.services.metrics;

import com.otlp.receiver.services.Result;
import com.otlp.receiver.utils.Exceptions;
import io.opentelemetry.proto.collector.metrics.v1.ExportMetricsPartialSuccess;
import io.opentelemetry.proto.collector.metrics.v1.ExportMetricsServiceRequest;
import io.opentelemetry.proto.collector.metrics.v1.ExportMetricsServiceResponse;
import io.opentelemetry.proto.metrics.v1.Metric;
import io.opentelemetry.proto.metrics.v1.ResourceMetrics;
import io.opentelemetry.proto.metrics.v1.ScopeMetrics;

import java.util.List;
import java.util.StringJoiner;

public class MetricsRequestService {
    private static void persistResourceMetricData(io.opentelemetry.proto.metrics.v1.ResourceMetrics resourceMetricM) throws Exceptions.ValidationError, Exceptions.ValidationWarning {
        for (io.opentelemetry.proto.metrics.v1.ScopeMetrics scopeMetricM : resourceMetricM.getScopeMetricsList()) {
            for (io.opentelemetry.proto.metrics.v1.Metric metricM : scopeMetricM.getMetricsList()) {
                MetricService.persistMetric(metricM, scopeMetricM, resourceMetricM);
            }
        }
    }

    private static Result processResourceMetric(io.opentelemetry.proto.metrics.v1.ResourceMetrics resourceMetricM) {
        try {
            persistResourceMetricData(resourceMetricM);
        } catch (Exceptions.ValidationError error) {
            return new Result(Result.Type.FAILURE, error.getMessage());
        } catch (Exceptions.ValidationWarning warning) {
            return new Result(Result.Type.WARNING, warning.getMessage());
        }

        return new Result(Result.Type.SUCCESS, "");
    }

    private static ExportMetricsPartialSuccess buildPartialSuccess(long rejectedItems, String errorMessage) {
        ExportMetricsPartialSuccess partialSuccess = null;
        if (rejectedItems != 0 || !errorMessage.isEmpty()) {
            partialSuccess = ExportMetricsPartialSuccess.newBuilder()
                    .setRejectedDataPoints(rejectedItems)
                    .setErrorMessage(errorMessage)
                    .build();
        }
        return partialSuccess;
    }

    public static ExportMetricsServiceResponse processMetricRequest(ExportMetricsServiceRequest request) throws Exceptions.InvalidArguments {
        long rejectedItems = 0;
        StringJoiner errorMessageJoiner = new StringJoiner("\n");
        List<ResourceMetrics> resourceMetrics = request.getResourceMetricsList();
        for (io.opentelemetry.proto.metrics.v1.ResourceMetrics resourceMetric : resourceMetrics) {
            Result result = processResourceMetric(resourceMetric);
            if (result.getType() == Result.Type.FAILURE)
                rejectedItems += 1;
            errorMessageJoiner.add(result.getMessage());
        }
        if (rejectedItems == resourceMetrics.size())
            throw new Exceptions.InvalidArguments(errorMessageJoiner.toString());

        ExportMetricsPartialSuccess partialSuccess = buildPartialSuccess(rejectedItems, errorMessageJoiner.toString());
        ExportMetricsServiceResponse.Builder responseBuilder = ExportMetricsServiceResponse.newBuilder();
        if (partialSuccess == null)
            return responseBuilder.clearPartialSuccess().build();
        return responseBuilder.setPartialSuccess(partialSuccess).build();
    }
}
