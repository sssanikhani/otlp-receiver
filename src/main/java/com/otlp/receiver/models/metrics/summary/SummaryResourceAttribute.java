package com.otlp.receiver.models.metrics.summary;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class SummaryResourceAttribute extends BaseAttribute<Summary> {
    public SummaryResourceAttribute(String key, String value, Summary parent) {
        super(key, value, parent);
    }

    public SummaryResourceAttribute() {
    }
}
