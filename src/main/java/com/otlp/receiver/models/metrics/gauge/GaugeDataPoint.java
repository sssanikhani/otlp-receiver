package com.otlp.receiver.models.metrics.gauge;

import com.otlp.receiver.models.metrics.NumberDataPoint;
import io.opentelemetry.proto.metrics.v1.Exemplar;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class GaugeDataPoint extends NumberDataPoint {
    @JoinColumn(nullable = false)
    @ManyToOne
    private Gauge gauge;

    public GaugeDataPoint() {
    }

    public GaugeDataPoint(Exemplar.ValueCase valueType, double doubleValue, Long intValue, Gauge gauge) {
        super(valueType, doubleValue, intValue);
        this.gauge = gauge;
    }

    public Gauge getGauge() {
        return gauge;
    }

    public void setGauge(Gauge gauge) {
        this.gauge = gauge;
    }
}
