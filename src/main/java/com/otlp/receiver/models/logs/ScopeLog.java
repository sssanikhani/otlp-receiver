package com.otlp.receiver.models.logs;

import com.otlp.receiver.models.common.InstrumentationScope;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Set;

@Entity
public class ScopeLog extends AbstractPersistable<Long> {

    @ManyToOne
    private InstrumentationScope instrumentationScope;

    @OneToMany(mappedBy = "scopeLog")
    private Set<LogRecord> logRecords;

    private String schemaUrl;

    @ManyToOne
    private ResourceLog resourceLog;

    public ScopeLog() {
    }

    public ScopeLog(InstrumentationScope instrumentationScope, String schemaUrl, ResourceLog resourceLog) {
        this.instrumentationScope = instrumentationScope;
        this.schemaUrl = schemaUrl;
        this.resourceLog = resourceLog;
    }

    public InstrumentationScope getInstrumentationScope() {
        return instrumentationScope;
    }

    public void setInstrumentationScope(InstrumentationScope instrumentationScope) {
        this.instrumentationScope = instrumentationScope;
    }

    public Set<LogRecord> getLogRecords() {
        return logRecords;
    }

    public void setLogRecords(Set<LogRecord> logRecords) {
        this.logRecords = logRecords;
    }

    public String getSchemaUrl() {
        return schemaUrl;
    }

    public void setSchemaUrl(String schemaUrl) {
        this.schemaUrl = schemaUrl;
    }

    public ResourceLog getResourceLog() {
        return resourceLog;
    }

    public void setResourceLog(ResourceLog resourceLog) {
        this.resourceLog = resourceLog;
    }
}
