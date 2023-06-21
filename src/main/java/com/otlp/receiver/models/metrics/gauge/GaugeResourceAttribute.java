package com.otlp.receiver.models.metrics.gauge;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class GaugeResourceAttribute extends BaseAttribute<Gauge> {
    public GaugeResourceAttribute() {
    }

    public GaugeResourceAttribute(String key, String value, Gauge parent) {
        super(key, value, parent);
    }
}
