package com.otlp.receiver.models.metrics;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseHistogramDataPoint extends BaseDataPoint{
    private Long count = 0L;
    private double sum = 0;
    private double min;
    private double max;

    public BaseHistogramDataPoint() {
    }

    public BaseHistogramDataPoint(Long count, double sum, double min, double max) {
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

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
}
