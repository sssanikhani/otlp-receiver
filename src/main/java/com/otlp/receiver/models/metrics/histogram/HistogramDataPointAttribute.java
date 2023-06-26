package com.otlp.receiver.models.metrics.histogram;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class HistogramDataPointAttribute extends BaseAttribute<HistogramDataPoint> {
    public HistogramDataPointAttribute(String key, String value, HistogramDataPoint parent) {
        super(key, value, parent);
    }

    public HistogramDataPointAttribute() {
    }
}
