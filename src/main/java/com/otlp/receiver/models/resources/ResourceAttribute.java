package com.otlp.receiver.models.resources;

import com.otlp.receiver.models.common.BaseKeyValue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ResourceAttribute extends BaseKeyValue {
    @Column(nullable = false)
    @ManyToOne
    private Resource resource;

    public Resource getLogRecord() {
        return resource;
    }

    public void setLogRecord(Resource resource) {
        this.resource = resource;
    }
}
