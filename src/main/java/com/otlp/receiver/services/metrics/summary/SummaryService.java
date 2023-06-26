package com.otlp.receiver.services.metrics.summary;

import com.otlp.receiver.models.metrics.summary.Summary;
import com.otlp.receiver.utils.ObjectPersister;

public class SummaryService {
    public static void persistSummary(io.opentelemetry.proto.metrics.v1.Metric metricM, io.opentelemetry.proto.metrics.v1.ScopeMetrics scopeMetricM, io.opentelemetry.proto.metrics.v1.ResourceMetrics resourceMetricM) {
        Summary summary = new Summary(scopeMetricM.getScope().getName(), scopeMetricM.getScope().getVersion(), resourceMetricM.getSchemaUrl(), scopeMetricM.getSchemaUrl(), metricM.getName(), metricM.getDescription(), metricM.getUnit());
        ObjectPersister.persist(summary);
        SummaryDataPointService.persistBatch(metricM.getSummary().getDataPointsList(), summary);
    }
}
