package com.otlp.receiver.models.metrics.gauge;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class GaugeDataPointAttribute extends BaseAttribute<GaugeDataPoint> {
    public GaugeDataPointAttribute(String key, String value, GaugeDataPoint parent) {
        super(key, value, parent);
    }

    public GaugeDataPointAttribute() {
    }
}
