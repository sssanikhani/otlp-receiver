package com.otlp.receiver.models.metrics.gauge;

import com.otlp.receiver.models.metrics.Exemplar;
import com.otlp.receiver.models.metrics.NumberDataPoint;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

@Entity
public class GaugeDataPoint extends NumberDataPoint {
    @JoinColumn(nullable = false)
    @ManyToOne
    private Gauge gauge;

    @OneToMany(mappedBy = "gaugeDataPoint")
    private Set<Exemplar> exemplars;

    public GaugeDataPoint() {
    }

    public GaugeDataPoint(Long timeUnixNano, @Nullable Long startTimeUnixNano, Integer flags, io.opentelemetry.proto.metrics.v1.NumberDataPoint.ValueCase valueType, @Nullable Double doubleValue, @Nullable Long intValue, Gauge gauge) {
        super(timeUnixNano, startTimeUnixNano, flags, valueType, doubleValue, intValue);
        this.gauge = gauge;
    }

    public Gauge getGauge() {
        return gauge;
    }

    public void setGauge(Gauge gauge) {
        this.gauge = gauge;
    }

    public Set<Exemplar> getExemplars() {
        return exemplars;
    }

    public void setExemplars(Set<Exemplar> exemplars) {
        this.exemplars = exemplars;
    }
}
