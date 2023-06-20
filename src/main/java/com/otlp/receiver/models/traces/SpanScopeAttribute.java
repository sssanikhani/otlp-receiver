package com.otlp.receiver.models.traces;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class SpanScopeAttribute extends BaseAttribute<Span> {
    public SpanScopeAttribute() {
    }

    public SpanScopeAttribute(String key, String value, Span parent) {
        super(key, value, parent);
    }
}
