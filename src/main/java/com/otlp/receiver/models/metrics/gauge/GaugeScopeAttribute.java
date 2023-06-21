package com.otlp.receiver.models.metrics.gauge;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class GaugeScopeAttribute extends BaseAttribute<Gauge> {
    public GaugeScopeAttribute(String key, String value, Gauge parent) {
        super(key, value, parent);
    }

    public GaugeScopeAttribute() {
    }
}
