package com.otlp.receiver.models.metrics.histogram;

import com.otlp.receiver.models.metrics.BaseHistogramDataPoint;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class HistogramDataPoint extends BaseHistogramDataPoint {
    private long[] bucketCounts;
    private long[] explicitBound;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Histogram histogram;


    public HistogramDataPoint() {
    }

    public HistogramDataPoint(Long count, double sum, double min, double max, long[] bucketCounts, long[] explicitBound, Histogram histogram) {
        super(count, sum, min, max);
        this.bucketCounts = bucketCounts;
        this.explicitBound = explicitBound;
        this.histogram = histogram;
    }

    public long[] getBucketCounts() {
        return bucketCounts;
    }

    public void setBucketCounts(long[] bucketCounts) {
        this.bucketCounts = bucketCounts;
    }

    public long[] getExplicitBound() {
        return explicitBound;
    }

    public void setExplicitBound(long[] explicitBound) {
        this.explicitBound = explicitBound;
    }

    public Histogram getHistogram() {
        return histogram;
    }

    public void setHistogram(Histogram histogram) {
        this.histogram = histogram;
    }
}
