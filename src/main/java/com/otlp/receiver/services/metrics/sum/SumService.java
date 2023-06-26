package com.otlp.receiver.services.metrics.sum;

import com.otlp.receiver.models.metrics.sum.Sum;
import com.otlp.receiver.utils.ObjectPersister;

public class SumService {
    public static void persistSum(io.opentelemetry.proto.metrics.v1.Metric metricM, io.opentelemetry.proto.metrics.v1.ScopeMetrics scopeMetricM, io.opentelemetry.proto.metrics.v1.ResourceMetrics resourceMetricM) {
        Sum sum = new Sum(scopeMetricM.getScope().getName(), scopeMetricM.getScope().getVersion(), resourceMetricM.getSchemaUrl(), scopeMetricM.getSchemaUrl(), metricM.getName(), metricM.getDescription(), metricM.getUnit());
        ObjectPersister.persist(sum);
        SumDataPointService.persistBatch(metricM.getSum().getDataPointsList(), sum);
    }
}
