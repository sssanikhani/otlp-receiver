package com.otlp.receiver.models.metrics.sum;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class SumDataPointAttribute extends BaseAttribute<SumDataPoint> {
    public SumDataPointAttribute(String key, String value, SumDataPoint parent) {
        super(key, value, parent);
    }

    public SumDataPointAttribute() {
    }
}
