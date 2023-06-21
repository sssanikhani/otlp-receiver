package com.otlp.receiver.models.metrics;

import io.opentelemetry.proto.metrics.v1.Exemplar;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class NumberDataPoint extends BaseDataPoint{
    @Enumerated
    private io.opentelemetry.proto.metrics.v1.Exemplar.ValueCase valueType;
    private double doubleValue;
    private Long intValue;

    public NumberDataPoint() {
    }

    public NumberDataPoint(Exemplar.ValueCase valueType, double doubleValue, Long intValue) {
        this.valueType = valueType;
        this.doubleValue = doubleValue;
        this.intValue = intValue;
    }

    public Exemplar.ValueCase getValueType() {
        return valueType;
    }

    public void setValueType(Exemplar.ValueCase valueType) {
        this.valueType = valueType;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Long getIntValue() {
        return intValue;
    }

    public void setIntValue(Long intValue) {
        this.intValue = intValue;
    }
}
