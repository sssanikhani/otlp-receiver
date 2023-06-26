package com.otlp.receiver.services.metrics.exponential_histogram;

import com.otlp.receiver.models.metrics.exponential_histogram.ExponentialHistogram;
import com.otlp.receiver.utils.ObjectPersister;

public class ExponentialHistogramService {
    public static void persistExponentialHistogram(io.opentelemetry.proto.metrics.v1.Metric metricM, io.opentelemetry.proto.metrics.v1.ScopeMetrics scopeMetricM, io.opentelemetry.proto.metrics.v1.ResourceMetrics resourceMetricM) {
        ExponentialHistogram exponentialHistogram = new ExponentialHistogram(scopeMetricM.getScope().getName(), scopeMetricM.getScope().getVersion(), resourceMetricM.getSchemaUrl(), scopeMetricM.getSchemaUrl(), metricM.getName(), metricM.getDescription(), metricM.getUnit(), metricM.getExponentialHistogram().getAggregationTemporality());
        ObjectPersister.persist(exponentialHistogram);
        ExponentialHistogramDataPointService.persistBatch(metricM.getExponentialHistogram().getDataPointsList(), exponentialHistogram);
    }
}
