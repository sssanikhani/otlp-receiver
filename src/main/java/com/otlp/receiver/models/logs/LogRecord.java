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
    private Long timeUnixNano = 0L;
    @Column(nullable = false)
    private Long observedTimeUnixNano = 0L;
    private String severityText;
    private String body;

    @OneToMany(mappedBy = "logRecord")
    private Set<LogRecordAttribute> attributes;
    private Long droppedAttributesCount = 0L;

    public LogRecord() {
    }

    public LogRecord(SeverityNumber severityNumber, Long timeUnixNano,
                     Long observedTimeUnixNano, String severityText, String body,
                     Long droppedAttributesCount, int flags, byte[] traceId, byte[] spanId, ScopeLog scopeLog) {
        this.severityNumber = severityNumber;
        this.timeUnixNano = timeUnixNano;
        this.observedTimeUnixNano = observedTimeUnixNano;
        this.severityText = severityText;
        this.body = body;
        this.droppedAttributesCount = droppedAttributesCount;
        this.flags = flags;
        this.traceId = traceId;
        this.spanId = spanId;
        this.scopeLog = scopeLog;
    }

    @Column(nullable = false)
    private int flags;

    private byte[] traceId;

    private byte[] spanId;

    @ManyToOne
    private ScopeLog scopeLog;

    public SeverityNumber getSeverityNumber() {
        return severityNumber;
    }

    public void setSeverityNumber(SeverityNumber severityNumber) {
        this.severityNumber = severityNumber;
    }

    public Long getTimeUnixNano() {
        return timeUnixNano;
    }

    public void setTimeUnixNano(Long timeUnixNano) {
        this.timeUnixNano = timeUnixNano;
    }

    public Long getObservedTimeUnixNano() {
        return observedTimeUnixNano;
    }

    public void setObservedTimeUnixNano(Long observedTimeUnixNano) {
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

    public Long getDroppedAttributesCount() {
        return droppedAttributesCount;
    }

    public void setDroppedAttributesCount(Long droppedAttributesCount) {
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
