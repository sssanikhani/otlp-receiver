package com.otlp.receiver.models.metrics;

import io.opentelemetry.proto.metrics.v1.AggregationTemporality;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseHistogram<RESOURCE_ATTR, SCOPE_ATTR> extends Metric<RESOURCE_ATTR, SCOPE_ATTR> {
    @Enumerated
    private AggregationTemporality aggregationTemporality = AggregationTemporality.AGGREGATION_TEMPORALITY_UNSPECIFIED;

    public AggregationTemporality getAggregationTemporality() {
        return aggregationTemporality;
    }

    public void setAggregationTemporality(AggregationTemporality aggregationTemporality) {
        this.aggregationTemporality = aggregationTemporality;
    }

    public BaseHistogram() {
    }

    public BaseHistogram(String scopeName, String scopeVersion, String resourceSchemaUrl, String schemaUrl, String name, String description, String unit, AggregationTemporality aggregationTemporality) {
        super(scopeName, scopeVersion, resourceSchemaUrl, schemaUrl, name, description, unit);
        this.aggregationTemporality = aggregationTemporality;
    }
}
