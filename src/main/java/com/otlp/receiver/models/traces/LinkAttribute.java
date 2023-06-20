package com.otlp.receiver.models.traces;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class LinkAttribute extends BaseAttribute<Link> {
    public LinkAttribute() {
    }

    public LinkAttribute(String key, String value, Link parent) {
        super(key, value, parent);
    }
}
