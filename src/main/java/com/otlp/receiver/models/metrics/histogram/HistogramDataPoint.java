package com.otlp.receiver.models.metrics.histogram;

import com.otlp.receiver.models.metrics.BaseHistogramDataPoint;
import com.otlp.receiver.models.metrics.Exemplar;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

@Entity
public class HistogramDataPoint extends BaseHistogramDataPoint {
    private long[] bucketCounts;
    private double[] explicitBound;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Histogram histogram;

    @OneToMany(mappedBy = "histogramDataPoint")
    private Set<Exemplar> exemplars;


    public HistogramDataPoint() {
    }

    public HistogramDataPoint(Long timeUnixNano, @Nullable Long startTimeUnixNano, Integer flags, Long count, Double sum, Double min, Double max, long[] bucketCounts, double[] explicitBound, Histogram histogram) {
        super(timeUnixNano, startTimeUnixNano, flags, count, sum, min, max);
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

    public double[] getExplicitBound() {
        return explicitBound;
    }

    public void setExplicitBound(double[] explicitBound) {
        this.explicitBound = explicitBound;
    }

    public Histogram getHistogram() {
        return histogram;
    }

    public void setHistogram(Histogram histogram) {
        this.histogram = histogram;
    }

    public Set<Exemplar> getExemplars() {
        return exemplars;
    }

    public void setExemplars(Set<Exemplar> exemplars) {
        this.exemplars = exemplars;
    }
}
