package com.otlp.receiver.services.metrics;

import io.opentelemetry.proto.metrics.v1.Metric;
import io.opentelemetry.proto.metrics.v1.ResourceMetrics;
import io.opentelemetry.proto.metrics.v1.ScopeMetrics;
import com.otlp.receiver.services.metrics.sum.SumService;
import com.otlp.receiver.services.metrics.gauge.GaugeService;
import com.otlp.receiver.services.metrics.histogram.HistogramService;
import com.otlp.receiver.services.metrics.exponential_histogram.ExponentialHistogramService;
import com.otlp.receiver.services.metrics.summary.SummaryService;

public class MetricService {
    public static void persistMetric(Metric metricM, ScopeMetrics scopeMetricM, ResourceMetrics resourceMetricM) {
        switch (metricM.getDataCase()) {
            case SUM -> {
                SumService.persistSum(metricM, scopeMetricM, resourceMetricM);
            }
            case GAUGE -> {
                GaugeService.persistGauge(metricM, scopeMetricM, resourceMetricM);
            }
            case HISTOGRAM -> {
                HistogramService.persistHistogram(metricM, scopeMetricM, resourceMetricM);
            }
            case EXPONENTIAL_HISTOGRAM -> {
                ExponentialHistogramService.persistExponentialHistogram(metricM, scopeMetricM, resourceMetricM);
            }
            case SUMMARY -> {
                SummaryService.persistSummary(metricM, scopeMetricM, resourceMetricM);
            }
            default -> {
            }
        }
    }
}
