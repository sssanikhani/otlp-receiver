package com.otlp.receiver.models.metrics.histogram;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class HistogramScopeAttribute extends BaseAttribute<Histogram> {
    public HistogramScopeAttribute(String key, String value, Histogram parent) {
        super(key, value, parent);
    }

    public HistogramScopeAttribute() {
    }
}
