package com.otlp.receiver.models.metrics;

import com.otlp.receiver.models.metrics.exponential_histogram.ExponentialHistogramDataPoint;
import com.otlp.receiver.models.metrics.gauge.GaugeDataPoint;
import com.otlp.receiver.models.metrics.histogram.HistogramDataPoint;
import com.otlp.receiver.models.metrics.sum.SumDataPoint;
import com.otlp.receiver.models.metrics.summary.SummaryDataPoint;
import com.otlp.receiver.models.traces.Span;
import com.otlp.receiver.models.traces.Trace;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Set;

@Entity
public class Exemplar extends AbstractPersistable<Long> {
    @OneToMany(mappedBy = "parent")
    private Set<ExemplarFilteredAttribute> filteredAttributes;

    private Long timeUnixNano;

    @Enumerated
    private io.opentelemetry.proto.metrics.v1.Exemplar.ValueCase valueType;
    private double doubleValue;
    private Long intValue;

    @ManyToOne
    private Span span;

    @ManyToOne
    private Trace trace;

    @ManyToOne
    private SumDataPoint sumDataPoint;
    @ManyToOne
    private GaugeDataPoint gaugeDataPoint;
    @ManyToOne
    private HistogramDataPoint histogramDataPoint;
    @ManyToOne
    private ExponentialHistogramDataPoint exponentialHistogramDataPoint;
    @ManyToOne
    private SummaryDataPoint summaryDataPoint;

    public Exemplar() {
    }

    public Exemplar(Long timeUnixNano, io.opentelemetry.proto.metrics.v1.Exemplar.ValueCase valueType, double doubleValue, Long intValue, Span span, Trace trace, SumDataPoint sumDataPoint, GaugeDataPoint gaugeDataPoint, HistogramDataPoint histogramDataPoint, ExponentialHistogramDataPoint exponentialHistogramDataPoint, SummaryDataPoint summaryDataPoint) {
        this.timeUnixNano = timeUnixNano;
        this.valueType = valueType;
        this.doubleValue = doubleValue;
        this.intValue = intValue;
        this.span = span;
        this.trace = trace;
        this.sumDataPoint = sumDataPoint;
        this.gaugeDataPoint = gaugeDataPoint;
        this.histogramDataPoint = histogramDataPoint;
        this.exponentialHistogramDataPoint = exponentialHistogramDataPoint;
        this.summaryDataPoint = summaryDataPoint;
    }

    public Set<ExemplarFilteredAttribute> getFilteredAttributes() {
        return filteredAttributes;
    }

    public void setFilteredAttributes(Set<ExemplarFilteredAttribute> filteredAttributes) {
        this.filteredAttributes = filteredAttributes;
    }

    public Long getTimeUnixNano() {
        return timeUnixNano;
    }

    public void setTimeUnixNano(Long timeUnixNano) {
        this.timeUnixNano = timeUnixNano;
    }

    public io.opentelemetry.proto.metrics.v1.Exemplar.ValueCase getValueType() {
        return valueType;
    }

    public void setValueType(io.opentelemetry.proto.metrics.v1.Exemplar.ValueCase valueType) {
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

    public Span getSpan() {
        return span;
    }

    public void setSpan(Span span) {
        this.span = span;
    }

    public Trace getTrace() {
        return trace;
    }

    public void setTrace(Trace trace) {
        this.trace = trace;
    }

    public SumDataPoint getSumDataPoint() {
        return sumDataPoint;
    }

    public void setSumDataPoint(SumDataPoint sumDataPoint) {
        this.sumDataPoint = sumDataPoint;
    }

    public GaugeDataPoint getGaugeDataPoint() {
        return gaugeDataPoint;
    }

    public void setGaugeDataPoint(GaugeDataPoint gaugeDataPoint) {
        this.gaugeDataPoint = gaugeDataPoint;
    }

    public HistogramDataPoint getHistogramDataPoint() {
        return histogramDataPoint;
    }

    public void setHistogramDataPoint(HistogramDataPoint histogramDataPoint) {
        this.histogramDataPoint = histogramDataPoint;
    }

    public ExponentialHistogramDataPoint getExponentialHistogramDataPoint() {
        return exponentialHistogramDataPoint;
    }

    public void setExponentialHistogramDataPoint(ExponentialHistogramDataPoint exponentialHistogramDataPoint) {
        this.exponentialHistogramDataPoint = exponentialHistogramDataPoint;
    }

    public SummaryDataPoint getSummaryDataPoint() {
        return summaryDataPoint;
    }

    public void setSummaryDataPoint(SummaryDataPoint summaryDataPoint) {
        this.summaryDataPoint = summaryDataPoint;
    }
}
