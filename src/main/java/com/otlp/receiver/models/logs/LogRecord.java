package com.otlp.receiver.models.logs;

import io.opentelemetry.proto.logs.v1.SeverityNumber;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Set;

@Entity
public class LogRecord extends AbstractPersistable<Long> {
    @Enumerated
    @Column(nullable = false)
    private SeverityNumber severityNumber = SeverityNumber.SEVERITY_NUMBER_UNSPECIFIED;

    @Column(nullable = false)
    private long timeUnixNano = 0;
    @Column(nullable = false)
    private long observedTimeUnixNano = 0;
    private String severityText;
    private String body;

    @OneToMany(mappedBy = "logRecord")
    private Set<LogRecordAttribute> attributes;
    private long droppedAttributesCount = 0;

    @Column(nullable = false)
    private int flags;

    @Lob
    private byte[] traceId;

    @Lob
    private byte[] spanId;

    @ManyToOne
    private ScopeLog scopeLog;

    public SeverityNumber getSeverityNumber() {
        return severityNumber;
    }

    public void setSeverityNumber(SeverityNumber severityNumber) {
        this.severityNumber = severityNumber;
    }

    public long getTimeUnixNano() {
        return timeUnixNano;
    }

    public void setTimeUnixNano(long timeUnixNano) {
        this.timeUnixNano = timeUnixNano;
    }

    public long getObservedTimeUnixNano() {
        return observedTimeUnixNano;
    }

    public void setObservedTimeUnixNano(long observedTimeUnixNano) {
        this.observedTimeUnixNano = observedTimeUnixNano;
    }

    public String getSeverityText() {
        return severityText;
    }

    public void setSeverityText(String severityText) {
        this.severityText = severityText;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Set<LogRecordAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<LogRecordAttribute> attributes) {
        this.attributes = attributes;
    }

    public long getDroppedAttributesCount() {
        return droppedAttributesCount;
    }

    public void setDroppedAttributesCount(long droppedAttributesCount) {
        this.droppedAttributesCount = droppedAttributesCount;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public byte[] getTraceId() {
        return traceId;
    }

    public void setTraceId(byte[] traceId) {
        this.traceId = traceId;
    }

    public byte[] getSpanId() {
        return spanId;
    }

    public void setSpanId(byte[] spanId) {
        this.spanId = spanId;
    }

    public ScopeLog getScopeLog() {
        return scopeLog;
    }

    public void setScopeLog(ScopeLog scopeLog) {
        this.scopeLog = scopeLog;
    }
}
