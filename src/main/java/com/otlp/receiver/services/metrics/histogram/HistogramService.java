package com.otlp.receiver.services.metrics.histogram;

import com.otlp.receiver.models.metrics.histogram.Histogram;
import com.otlp.receiver.utils.ObjectPersister;

public class HistogramService {
    public static void persistHistogram(io.opentelemetry.proto.metrics.v1.Metric metricM, io.opentelemetry.proto.metrics.v1.ScopeMetrics scopeMetricM, io.opentelemetry.proto.metrics.v1.ResourceMetrics resourceMetricM) {
        Histogram histogram = new Histogram(scopeMetricM.getScope().getName(),
                scopeMetricM.getScope().getVersion(), resourceMetricM.getSchemaUrl(),
                scopeMetricM.getSchemaUrl(), metricM.getName(), metricM.getDescription(),
                metricM.getUnit(), metricM.getHistogram().getAggregationTemporality());
        ObjectPersister.persist(histogram);
        HistogramDataPointService.persistBatch(metricM.getHistogram().getDataPointsList(), histogram);
    }
}
