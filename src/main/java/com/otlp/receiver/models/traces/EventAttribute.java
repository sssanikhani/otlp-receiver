package com.otlp.receiver.models.traces;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class EventAttribute extends BaseAttribute<Event> {
    public EventAttribute() {
    }

    public EventAttribute(String key, String value, Event parent) {
        super(key, value, parent);
    }
}
