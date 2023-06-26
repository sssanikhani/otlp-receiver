package com.otlp.receiver.models.metrics;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class ExemplarFilteredAttribute extends BaseAttribute<Exemplar> {
    public ExemplarFilteredAttribute(String key, String value, Exemplar parent) {
        super(key, value, parent);
    }

    public ExemplarFilteredAttribute() {
    }
}
