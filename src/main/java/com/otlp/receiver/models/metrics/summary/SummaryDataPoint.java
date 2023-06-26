package com.otlp.receiver.models.metrics.summary;

import com.otlp.receiver.models.metrics.BaseDataPoint;
import com.otlp.receiver.models.metrics.Exemplar;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
public class SummaryDataPoint extends BaseDataPoint {
    private Long count;
    private Double sum;
    private double[] quantiles;
    private double[] quantileValues;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Summary summary;

    @OneToMany(mappedBy = "summaryDataPoint")
    private Set<Exemplar> exemplars;

    public SummaryDataPoint() {
    }

    public SummaryDataPoint(Long timeUnixNano, @Nullable Long startTimeUnixNano, Integer flags, Long count, Double sum, double[] quantiles, double[] quantileValues, Summary summary) {
        super(timeUnixNano, startTimeUnixNano, flags);
        this.count = count;
        this.sum = sum;
        this.quantiles = quantiles;
        this.quantileValues = quantileValues;
        this.summary = summary;
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

    public double[] getQuantiles() {
        return quantiles;
    }

    public void setQuantiles(double[] quantiles) {
        this.quantiles = quantiles;
    }

    public double[] getQuantileValues() {
        return quantileValues;
    }

    public void setQuantileValues(double[] quantileValues) {
        this.quantileValues = quantileValues;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public Set<Exemplar> getExemplars() {
        return exemplars;
    }

    public void setExemplars(Set<Exemplar> exemplars) {
        this.exemplars = exemplars;
    }
}
