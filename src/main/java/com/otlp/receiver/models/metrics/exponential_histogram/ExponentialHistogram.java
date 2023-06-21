package com.otlp.receiver.models.metrics.exponential_histogram;

import com.otlp.receiver.models.metrics.BaseHistogram;
import io.opentelemetry.proto.metrics.v1.AggregationTemporality;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class ExponentialHistogram extends BaseHistogram<ExponentialHistogramResourceAttribute, ExponentialHistogramScopeAttribute> {
    @OneToMany(mappedBy = "exponentialHistogram")
    private Set<ExponentialHistogramDataPoint> dataPoints;

    public ExponentialHistogram() {
    }

    public ExponentialHistogram(String scopeName, String scopeVersion, String resourceSchemaUrl, String schemaUrl, String name, String description, String unit, AggregationTemporality aggregationTemporality) {
        super(scopeName, scopeVersion, resourceSchemaUrl, schemaUrl, name, description, unit, aggregationTemporality);
    }

    public Set<ExponentialHistogramDataPoint> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(Set<ExponentialHistogramDataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }
}
