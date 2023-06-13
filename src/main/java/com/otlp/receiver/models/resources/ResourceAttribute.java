package com.otlp.receiver.models.resources;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ResourceAttribute extends BaseAttribute {
    @JoinColumn(nullable = false)
    @ManyToOne
    private Resource resource;

    public ResourceAttribute() {}

    public ResourceAttribute(String key, String value, Resource resource) {
        super(key, value);
        this.resource = resource;
    }

    public Resource getLogRecord() {
        return resource;
    }

    public void setLogRecord(Resource resource) {
        this.resource = resource;
    }
}
