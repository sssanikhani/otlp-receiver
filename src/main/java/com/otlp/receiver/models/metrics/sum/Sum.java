package com.otlp.receiver.models.metrics.sum;

import com.otlp.receiver.models.metrics.Metric;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Sum extends Metric<SumResourceAttribute, SumScopeAttribute> {
    @OneToMany(mappedBy = "sum")
    private Set<SumDataPoint> dataPoints;

    public Sum() {
    }

    public Sum(String scopeName, String scopeVersion, String resourceSchemaUrl, String schemaUrl, String name, String description, String unit) {
        super(scopeName, scopeVersion, resourceSchemaUrl, schemaUrl, name, description, unit);
    }

    public Set<SumDataPoint> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(Set<SumDataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }
}
