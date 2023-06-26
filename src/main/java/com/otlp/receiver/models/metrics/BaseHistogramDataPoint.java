package com.otlp.receiver.models.metrics;

import jakarta.persistence.MappedSuperclass;
import org.jetbrains.annotations.Nullable;

@MappedSuperclass
public class BaseHistogramDataPoint extends BaseDataPoint{
    private Long count = 0L;
    private Double sum;
    private Double min;
    private Double max;

    public BaseHistogramDataPoint() {
    }

    public BaseHistogramDataPoint(Long timeUnixNano, @Nullable Long startTimeUnixNano, Integer flags, Long count, Double sum, Double min, Double max) {
        super(timeUnixNano, startTimeUnixNano, flags);
        this.count = count;
        this.sum = sum;
        this.min = min;
        this.max = max;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }
}
