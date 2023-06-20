package com.otlp.receiver.models.traces;

import com.otlp.receiver.models.logs.LogRecord;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Set;

@Entity
public class Trace extends AbstractPersistable<Long> {
    @Column(unique = true)
    private byte[] traceId;

    private String state;

    @OneToMany(mappedBy = "trace")
    private Set<Span> spans;

    @OneToMany(mappedBy = "trace")
    private Set<LogRecord> logRecords;

    public Trace() {
    }

    public Trace(byte[] traceId, String state) {
        this.traceId = traceId;
        this.state = state;
    }

    public byte[] getTraceId() {
        return traceId;
    }

    public void setTraceId(byte[] traceId) {
        this.traceId = traceId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<Span> getSpans() {
        return spans;
    }

    public void setSpans(Set<Span> spans) {
        this.spans = spans;
    }

    public Set<LogRecord> getLogRecords() {
        return logRecords;
    }

    public void setLogRecords(Set<LogRecord> logRecords) {
        this.logRecords = logRecords;
    }
}
