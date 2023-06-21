package com.otlp.receiver.models.metrics;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class ExemplarAttribute extends BaseAttribute<Exemplar> {
    public ExemplarAttribute(String key, String value, Exemplar parent) {
        super(key, value, parent);
    }

    public ExemplarAttribute() {
    }
}
