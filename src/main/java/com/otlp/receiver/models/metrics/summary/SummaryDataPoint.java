package com.otlp.receiver.models.metrics.summary;

import com.otlp.receiver.models.metrics.BaseDataPoint;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SummaryDataPoint extends BaseDataPoint {
    private Long count;
    private double sum;
    private String quantileValues;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Summary summary;

    public SummaryDataPoint() {
    }

    public SummaryDataPoint(Long timeUnixNano, Long startTimeUnixNano, Long flags, Long count, double sum, String quantileValues, Summary summary) {
        super(timeUnixNano, startTimeUnixNano, flags);
        this.count = count;
        this.sum = sum;
        this.quantileValues = quantileValues;
        this.summary = summary;
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

    public String getQuantileValues() {
        return quantileValues;
    }

    public void setQuantileValues(String quantileValues) {
        this.quantileValues = quantileValues;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }
}
