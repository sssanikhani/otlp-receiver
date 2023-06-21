package com.otlp.receiver.models.metrics.sum;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class SumScopeAttribute extends BaseAttribute<Sum> {
    public SumScopeAttribute(String key, String value, Sum parent) {
        super(key, value, parent);
    }

    public SumScopeAttribute() {
    }
}
