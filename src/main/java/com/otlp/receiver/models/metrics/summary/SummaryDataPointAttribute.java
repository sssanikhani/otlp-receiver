package com.otlp.receiver.models.metrics.summary;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class SummaryDataPointAttribute extends BaseAttribute<SummaryDataPoint> {
    public SummaryDataPointAttribute(String key, String value, SummaryDataPoint parent) {
        super(key, value, parent);
    }

    public SummaryDataPointAttribute() {
    }
}
