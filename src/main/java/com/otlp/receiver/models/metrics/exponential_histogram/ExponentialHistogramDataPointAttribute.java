package com.otlp.receiver.models.metrics.exponential_histogram;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class ExponentialHistogramDataPointAttribute extends BaseAttribute<ExponentialHistogramDataPoint> {
    public ExponentialHistogramDataPointAttribute(String key, String value, ExponentialHistogramDataPoint parent) {
        super(key, value, parent);
    }

    public ExponentialHistogramDataPointAttribute() {
    }
}
