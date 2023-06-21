package com.otlp.receiver.models.metrics.histogram;

import com.otlp.receiver.models.metrics.BaseHistogram;
import io.opentelemetry.proto.metrics.v1.AggregationTemporality;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Histogram extends BaseHistogram<HistogramResourceAttribute, HistogramScopeAttribute> {
    @OneToMany(mappedBy = "histogram")
    private Set<HistogramDataPoint> dataPoints;

    public Histogram() {
    }

    public Histogram(String scopeName, String scopeVersion, String resourceSchemaUrl, String schemaUrl, String name, String description, String unit, AggregationTemporality aggregationTemporality) {
        super(scopeName, scopeVersion, resourceSchemaUrl, schemaUrl, name, description, unit, aggregationTemporality);
    }

    public Set<HistogramDataPoint> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(Set<HistogramDataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }
}
