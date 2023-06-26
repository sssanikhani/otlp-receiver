package com.otlp.receiver.models.metrics.sum;

import com.otlp.receiver.models.metrics.Exemplar;
import com.otlp.receiver.models.metrics.NumberDataPoint;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

@Entity
public class SumDataPoint extends NumberDataPoint {
    @JoinColumn(nullable = false)
    @ManyToOne
    private Sum sum;

    @OneToMany(mappedBy = "sumDataPoint")
    private Set<Exemplar> exemplars;

    public SumDataPoint() {
    }

    public SumDataPoint(Long timeUnixNano, @Nullable Long startTimeUnixNano, Integer flags, io.opentelemetry.proto.metrics.v1.NumberDataPoint.ValueCase valueType, @Nullable Double doubleValue, @Nullable Long intValue, Sum sum) {
        super(timeUnixNano, startTimeUnixNano, flags, valueType, doubleValue, intValue);
        this.sum = sum;
    }

    public Sum getSum() {
        return sum;
    }

    public void setSum(Sum sum) {
        this.sum = sum;
    }

    public Set<Exemplar> getExemplars() {
        return exemplars;
    }

    public void setExemplars(Set<Exemplar> exemplars) {
        this.exemplars = exemplars;
    }
}
