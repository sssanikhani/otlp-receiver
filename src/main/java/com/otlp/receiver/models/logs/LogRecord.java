package com.otlp.receiver.models.logs;

import com.otlp.receiver.models.common.BaseSignal;
import com.otlp.receiver.models.traces.Span;
import com.otlp.receiver.models.traces.Trace;
import io.opentelemetry.proto.logs.v1.SeverityNumber;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class LogRecord extends BaseSignal<LogResourceAttribute, LogScopeAttribute> {
    @Enumerated
    @Column(nullable = false)
    private SeverityNumber severityNumber = SeverityNumber.SEVERITY_NUMBER_UNSPECIFIED;

    @Column(nullable = false)
    private Long timeUnixNano = 0L;
    @Column(nullable = false)
    private Long observedTimeUnixNano = 0L;
    private String severityText;
    private String body;

    @OneToMany(mappedBy = "parent")
    private Set<LogRecordAttribute> attributes;

    @ManyToOne
    private Span span;

    @ManyToOne
    private Trace trace;

    @Column(nullable = false)
    private int flags;

    public LogRecord() {
    }

    public LogRecord(String scopeName, String scopeVersion, String resourceSchemaUrl,
                     String schemaUrl, SeverityNumber severityNumber, Long timeUnixNano,
                     Long observedTimeUnixNano, String severityText, String body, Span span, Trace trace, int flags) {
        super(scopeName, scopeVersion, resourceSchemaUrl, schemaUrl);
        this.severityNumber = severityNumber;
        this.timeUnixNano = timeUnixNano;
        this.observedTimeUnixNano = observedTimeUnixNano;
        this.severityText = severityText;
        this.body = body;
        this.span = span;
        this.trace = trace;
        this.flags = flags;
    }

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

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public Span getSpan() {
        return span;
    }

    public void setSpan(Span span) {
        this.span = span;
    }

    public Trace getTrace() {
        return trace;
    }

    public void setTrace(Trace trace) {
        this.trace = trace;
    }
}
