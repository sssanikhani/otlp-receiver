package com.otlp.receiver.models.traces;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class SpanAttribute extends BaseAttribute<Span> {
    public SpanAttribute() {
    }

    public SpanAttribute(String key, String value, Span parent) {
        super(key, value, parent);
    }
}
