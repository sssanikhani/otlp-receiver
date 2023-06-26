package com.otlp.receiver.models.metrics.exponential_histogram;

import com.otlp.receiver.models.metrics.BaseHistogramDataPoint;
import com.otlp.receiver.models.metrics.Exemplar;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

@Entity
public class ExponentialHistogramDataPoint extends BaseHistogramDataPoint {
    private Integer scale;
    private Long zeroCount;
    private Double zeroThreshold;

    private Integer positiveBucketOffset;
    private long[] positiveBucketCounts;
    private Integer negativeBucketOffset;
    private long[] negativeBucketCounts;

    @JoinColumn(nullable = false)
    @ManyToOne
    private ExponentialHistogram exponentialHistogram;

    @OneToMany(mappedBy = "exponentialHistogramDataPoint")
    private Set<Exemplar> exemplars;

    public ExponentialHistogramDataPoint() {
    }

    public ExponentialHistogramDataPoint(Long timeUnixNano, @Nullable Long startTimeUnixNano, Integer flags, Long count, Double sum, Double min, Double max, Integer scale, Long zeroCount, Double zeroThreshold, Integer positiveBucketOffset, long[] positiveBucketCounts, Integer negativeBucketOffset, long[] negativeBucketCounts, ExponentialHistogram exponentialHistogram) {
        super(timeUnixNano, startTimeUnixNano, flags, count, sum, min, max);
        this.scale = scale;
        this.zeroCount = zeroCount;
        this.zeroThreshold = zeroThreshold;
        this.positiveBucketOffset = positiveBucketOffset;
        this.positiveBucketCounts = positiveBucketCounts;
        this.negativeBucketOffset = negativeBucketOffset;
        this.negativeBucketCounts = negativeBucketCounts;
        this.exponentialHistogram = exponentialHistogram;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public Long getZeroCount() {
        return zeroCount;
    }

    public void setZeroCount(Long zeroCount) {
        this.zeroCount = zeroCount;
    }

    public Double getZeroThreshold() {
        return zeroThreshold;
    }

    public void setZeroThreshold(Double zeroThreshold) {
        this.zeroThreshold = zeroThreshold;
    }

    public Integer getPositiveBucketOffset() {
        return positiveBucketOffset;
    }

    public void setPositiveBucketOffset(Integer positiveBucketOffset) {
        this.positiveBucketOffset = positiveBucketOffset;
    }

    public long[] getPositiveBucketCounts() {
        return positiveBucketCounts;
    }

    public void setPositiveBucketCounts(long[] positiveBucketCounts) {
        this.positiveBucketCounts = positiveBucketCounts;
    }

    public Integer getNegativeBucketOffset() {
        return negativeBucketOffset;
    }

    public void setNegativeBucketOffset(Integer negativeBucketOffset) {
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

    public Set<Exemplar> getExemplars() {
        return exemplars;
    }

    public void setExemplars(Set<Exemplar> exemplars) {
        this.exemplars = exemplars;
    }
}
