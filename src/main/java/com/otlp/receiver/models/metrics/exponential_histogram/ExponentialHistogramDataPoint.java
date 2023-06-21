package com.otlp.receiver.models.metrics.exponential_histogram;

import com.otlp.receiver.models.metrics.BaseHistogramDataPoint;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ExponentialHistogramDataPoint extends BaseHistogramDataPoint {
    private int scale;
    private Long zeroCount;
    private double zeroThreshold;

    private int positiveBucketOffset;
    private long[] positiveBucketCounts;
    private int negativeBucketOffset;
    private long[] negativeBucketCounts;

    @JoinColumn(nullable = false)
    @ManyToOne
    private ExponentialHistogram exponentialHistogram;

    public ExponentialHistogramDataPoint() {
    }

    public ExponentialHistogramDataPoint(Long count, double sum, double min, double max, int scale, Long zeroCount, double zeroThreshold, int positiveBucketOffset, long[] positiveBucketCounts, int negativeBucketOffset, long[] negativeBucketCounts, ExponentialHistogram exponentialHistogram) {
        super(count, sum, min, max);
        this.scale = scale;
        this.zeroCount = zeroCount;
        this.zeroThreshold = zeroThreshold;
        this.positiveBucketOffset = positiveBucketOffset;
        this.positiveBucketCounts = positiveBucketCounts;
        this.negativeBucketOffset = negativeBucketOffset;
        this.negativeBucketCounts = negativeBucketCounts;
        this.exponentialHistogram = exponentialHistogram;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public Long getZeroCount() {
        return zeroCount;
    }

    public void setZeroCount(Long zeroCount) {
        this.zeroCount = zeroCount;
    }

    public double getZeroThreshold() {
        return zeroThreshold;
    }

    public void setZeroThreshold(double zeroThreshold) {
        this.zeroThreshold = zeroThreshold;
    }

    public int getPositiveBucketOffset() {
        return positiveBucketOffset;
    }

    public void setPositiveBucketOffset(int positiveBucketOffset) {
        this.positiveBucketOffset = positiveBucketOffset;
    }

    public long[] getPositiveBucketCounts() {
        return positiveBucketCounts;
    }

    public void setPositiveBucketCounts(long[] positiveBucketCounts) {
        this.positiveBucketCounts = positiveBucketCounts;
    }

    public int getNegativeBucketOffset() {
        return negativeBucketOffset;
    }

    public void setNegativeBucketOffset(int negativeBucketOffset) {
        this.negativeBucketOffset = negativeBucketOffset;
    }

    public long[] getNegativeBucketCounts() {
        return negativeBucketCounts;
    }

    public void setNegativeBucketCounts(long[] negativeBucketCounts) {
        this.negativeBucketCounts = negativeBucketCounts;
    }

    public ExponentialHistogram getExponentialHistogram() {
        return exponentialHistogram;
    }

    public void setExponentialHistogram(ExponentialHistogram exponentialHistogram) {
        this.exponentialHistogram = exponentialHistogram;
    }
}
