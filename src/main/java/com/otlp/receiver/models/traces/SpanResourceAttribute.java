package com.otlp.receiver.models.traces;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class SpanResourceAttribute extends BaseAttribute<Span> {
    public SpanResourceAttribute() {
    }

    public SpanResourceAttribute(String key, String value, Span parent) {
        super(key, value, parent);
    }
}
