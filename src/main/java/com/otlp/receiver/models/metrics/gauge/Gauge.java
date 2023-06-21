package com.otlp.receiver.models.metrics.gauge;

import com.otlp.receiver.models.metrics.Metric;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Gauge extends Metric<GaugeResourceAttribute, GaugeScopeAttribute> {
    @OneToMany(mappedBy = "gauge")
    private Set<GaugeDataPoint> dataPoints;

    public Gauge() {
    }

    public Gauge(String scopeName, String scopeVersion, String resourceSchemaUrl, String schemaUrl, String name, String description, String unit) {
        super(scopeName, scopeVersion, resourceSchemaUrl, schemaUrl, name, description, unit);
    }

    public Set<GaugeDataPoint> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(Set<GaugeDataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }

}
