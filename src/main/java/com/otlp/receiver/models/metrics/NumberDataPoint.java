package com.otlp.receiver.models.metrics;

import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import org.jetbrains.annotations.Nullable;

@MappedSuperclass
public class NumberDataPoint extends BaseDataPoint{
    @Enumerated
    private io.opentelemetry.proto.metrics.v1.NumberDataPoint.ValueCase valueType;
    private Double doubleValue;
    private Long intValue;

    public NumberDataPoint() {
    }

    public NumberDataPoint(Long timeUnixNano, @Nullable Long startTimeUnixNano, Integer flags, io.opentelemetry.proto.metrics.v1.NumberDataPoint.ValueCase valueType, @Nullable Double doubleValue, @Nullable Long intValue) {
        super(timeUnixNano, startTimeUnixNano, flags);
        this.valueType = valueType;
        this.doubleValue = doubleValue;
        this.intValue = intValue;
    }

    public io.opentelemetry.proto.metrics.v1.NumberDataPoint.ValueCase getValueType() {
        return valueType;
    }

    public void setValueType(io.opentelemetry.proto.metrics.v1.NumberDataPoint.ValueCase valueType) {
        this.valueType = valueType;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Long getIntValue() {
        return intValue;
    }

    public void setIntValue(Long intValue) {
        this.intValue = intValue;
    }
}
