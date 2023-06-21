package com.otlp.receiver.models.metrics.summary;

import com.otlp.receiver.models.metrics.Metric;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Summary extends Metric<SummaryResourceAttribute, SummaryScopeAttribute> {
    @OneToMany(mappedBy = "summary")
    private Set<SummaryDataPoint> dataPoints;

    public Summary() {
    }

    public Summary(String scopeName, String scopeVersion, String resourceSchemaUrl, String schemaUrl, String name, String description, String unit) {
        super(scopeName, scopeVersion, resourceSchemaUrl, schemaUrl, name, description, unit);
    }

    public Set<SummaryDataPoint> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(Set<SummaryDataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }
}
