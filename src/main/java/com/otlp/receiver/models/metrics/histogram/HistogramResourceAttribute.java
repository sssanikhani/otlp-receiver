package com.otlp.receiver.models.metrics.histogram;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class HistogramResourceAttribute extends BaseAttribute<Histogram> {
    public HistogramResourceAttribute(String key, String value, Histogram parent) {
        super(key, value, parent);
    }

    public HistogramResourceAttribute() {
    }
}
