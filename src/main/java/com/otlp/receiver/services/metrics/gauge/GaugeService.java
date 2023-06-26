package com.otlp.receiver.services.metrics.gauge;

import com.otlp.receiver.models.metrics.gauge.Gauge;
import com.otlp.receiver.services.metrics.sum.SumDataPointService;
import com.otlp.receiver.utils.ObjectPersister;

public class GaugeService {
    public static void persistGauge(io.opentelemetry.proto.metrics.v1.Metric metricM, io.opentelemetry.proto.metrics.v1.ScopeMetrics scopeMetricM, io.opentelemetry.proto.metrics.v1.ResourceMetrics resourceMetricM) {
        Gauge gauge = new Gauge(scopeMetricM.getScope().getName(), scopeMetricM.getScope().getVersion(), resourceMetricM.getSchemaUrl(), scopeMetricM.getSchemaUrl(), metricM.getName(), metricM.getDescription(), metricM.getUnit());
        ObjectPersister.persist(gauge);
        GaugeDataPointService.persistBatch(metricM.getGauge().getDataPointsList(), gauge);
    }
}
