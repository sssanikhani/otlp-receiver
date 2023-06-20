package com.otlp.receiver.models.common;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Set;

@MappedSuperclass
public class BaseSignal<RESOURCE_ATTR, SCOPE_ATTR> extends AbstractPersistable<Long> {

    @OneToMany(mappedBy = "parent")
    private Set<RESOURCE_ATTR> resourceAttributes;

    private String scopeName;
    private String scopeVersion;
    @OneToMany(mappedBy = "parent")
    private Set<SCOPE_ATTR> scopeAttributes;

    private String resourceSchemaUrl;
    private String schemaUrl;

    public BaseSignal() {
    }

    public BaseSignal(String scopeName, String scopeVersion, String resourceSchemaUrl, String schemaUrl) {
        this.scopeName = scopeName;
        this.scopeVersion = scopeVersion;
        this.resourceSchemaUrl = resourceSchemaUrl;
        this.schemaUrl = schemaUrl;
    }

    public Set<RESOURCE_ATTR> getResourceAttributes() {
        return resourceAttributes;
    }

    public void setResourceAttributes(Set<RESOURCE_ATTR> resourceAttributes) {
        this.resourceAttributes = resourceAttributes;
    }

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }

    public String getScopeVersion() {
        return scopeVersion;
    }

    public void setScopeVersion(String scopeVersion) {
        this.scopeVersion = scopeVersion;
    }

    public Set<SCOPE_ATTR> getScopeAttributes() {
        return scopeAttributes;
    }

    public void setScopeAttributes(Set<SCOPE_ATTR> scopeAttributes) {
        this.scopeAttributes = scopeAttributes;
    }

    public String getResourceSchemaUrl() {
        return resourceSchemaUrl;
    }

    public void setResourceSchemaUrl(String resourceSchemaUrl) {
        this.resourceSchemaUrl = resourceSchemaUrl;
    }

    public String getSchemaUrl() {
        return schemaUrl;
    }

    public void setSchemaUrl(String schemaUrl) {
        this.schemaUrl = schemaUrl;
    }
}
