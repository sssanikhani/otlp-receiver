package com.otlp.receiver.models.common;

import com.otlp.receiver.models.logs.ScopeLog;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Set;

@Entity
public class InstrumentationScope extends AbstractPersistable<Long> {
    private String name;
    private String version;

    @OneToMany(mappedBy = "instrumentationScope")
    private Set<InstrumentationScopeAttribute> attributes;
    private long droppedAttributesCount = 0;

    @OneToMany(mappedBy = "instrumentationScope")
    private Set<ScopeLog> scopeLogs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Set<InstrumentationScopeAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<InstrumentationScopeAttribute> attributes) {
        this.attributes = attributes;
    }

    public long getDroppedAttributesCount() {
        return droppedAttributesCount;
    }

    public void setDroppedAttributesCount(long droppedAttributesCount) {
        this.droppedAttributesCount = droppedAttributesCount;
    }

    public Set<ScopeLog> getScopeLogs() {
        return scopeLogs;
    }

    public void setScopeLogs(Set<ScopeLog> scopeLogs) {
        this.scopeLogs = scopeLogs;
    }
}
