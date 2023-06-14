package com.otlp.receiver.models.logs;

import com.otlp.receiver.models.resources.Resource;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Set;

@Entity
public class ResourceLog extends AbstractPersistable<Long> {
    @ManyToOne
    private Resource resource;

    @OneToMany(mappedBy = "resourceLog")
    private Set<ScopeLog> scopeLog;

    private String schemaUrl;

    public ResourceLog() {
    }

    public ResourceLog(Resource resource, String schemaUrl) {
        this.resource = resource;
        this.schemaUrl = schemaUrl;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Set<ScopeLog> getScopeLog() {
        return scopeLog;
    }

    public void setScopeLog(Set<ScopeLog> scopeLog) {
        this.scopeLog = scopeLog;
    }

    public String getSchemaUrl() {
        return schemaUrl;
    }

    public void setSchemaUrl(String schemaUrl) {
        this.schemaUrl = schemaUrl;
    }
}
