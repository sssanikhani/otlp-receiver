package com.otlp.receiver.models.metrics;

import com.otlp.receiver.models.common.BaseSignal;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Set;

@MappedSuperclass
public class Metric<RESOURCE_ATTR, SCOPE_ATTR> extends BaseSignal<RESOURCE_ATTR, SCOPE_ATTR> {
    @Column(unique = true)
    private String name;
    private String description;
    private String unit;

    public Metric() {
    }

    public Metric(String scopeName, String scopeVersion, String resourceSchemaUrl, String schemaUrl, String name, String description, String unit) {
        super(scopeName, scopeVersion, resourceSchemaUrl, schemaUrl);
        this.name = name;
        this.description = description;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
