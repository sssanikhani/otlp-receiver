package com.otlp.receiver.models.metrics.sum;

import com.otlp.receiver.models.metrics.NumberDataPoint;
import io.opentelemetry.proto.metrics.v1.Exemplar;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SumDataPoint extends NumberDataPoint {
    @JoinColumn(nullable = false)
    @ManyToOne
    private Sum sum;

    public SumDataPoint() {
    }

    public SumDataPoint(Exemplar.ValueCase valueType, double doubleValue, Long intValue, Sum sum) {
        super(valueType, doubleValue, intValue);
        this.sum = sum;
    }

    public Sum getSum() {
        return sum;
    }

    public void setSum(Sum sum) {
        this.sum = sum;
    }
}
