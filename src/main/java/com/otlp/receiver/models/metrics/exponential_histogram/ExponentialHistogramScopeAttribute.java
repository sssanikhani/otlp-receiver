package com.otlp.receiver.models.metrics.exponential_histogram;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class ExponentialHistogramScopeAttribute extends BaseAttribute<ExponentialHistogram> {
    public ExponentialHistogramScopeAttribute() {
    }

    public ExponentialHistogramScopeAttribute(String key, String value, ExponentialHistogram parent) {
        super(key, value, parent);
    }
}
