package com.otlp.receiver.models.metrics.exponential_histogram;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class ExponentialHistogramResourceAttribute extends BaseAttribute<ExponentialHistogram> {
    public ExponentialHistogramResourceAttribute() {
    }

    public ExponentialHistogramResourceAttribute(String key, String value, ExponentialHistogram parent) {
        super(key, value, parent);
    }
}
