package com.otlp.receiver.models.traces;

import com.otlp.receiver.models.common.BaseSignal;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"trace_id", "id"}))
public class Span extends BaseSignal<SpanResourceAttribute, SpanScopeAttribute> {
    @JoinColumn(nullable = false)
    @ManyToOne
    private Trace trace;

    @Column(unique = true)
    private byte[] spanId;
    @ManyToOne
    private Span parent;

    @Column(nullable = false)
    private String name;

    @Enumerated
    private io.opentelemetry.proto.trace.v1.Span.SpanKind kind = io.opentelemetry.proto.trace.v1.Span.SpanKind.SPAN_KIND_UNSPECIFIED;

    @Column(nullable = false)
    private Long startTimeUnixNano;

    @Column(nullable = false)
    private Long endTimeUnixNano;

    @OneToMany(mappedBy = "parent")
    private Set<SpanAttribute> attributes;

    @OneToMany(mappedBy = "parent")
    private Set<SpanResourceAttribute> spanResourceAttributes;

    @OneToMany(mappedBy = "parent")
    private Set<SpanScopeAttribute> spanScopeAttributes;

    @OneToMany(mappedBy = "span")
    private Set<Event> events;

    @OneToMany(mappedBy = "from")
    private Set<Link> links;

    @OneToOne
    private Link fromLink;

    @OneToMany(mappedBy = "parent")
    private Set<Span> children;

    private String statusMessage;
    @Enumerated
    private io.opentelemetry.proto.trace.v1.Status.StatusCode statusCode;

    public Span() {
    }

    public Span(String scopeName, String scopeVersion, String resourceSchemaUrl, String schemaUrl,
                Trace trace, byte[] spanId, Span parent, String name,
                io.opentelemetry.proto.trace.v1.Span.SpanKind kind, Long startTimeUnixNano, Long endTimeUnixNano,
                String statusMessage, io.opentelemetry.proto.trace.v1.Status.StatusCode statusCode) {
        super(scopeName, scopeVersion, resourceSchemaUrl, schemaUrl);
        this.trace = trace;
        this.spanId = spanId;
        this.parent = parent;
        this.name = name;
        this.kind = kind;
        this.startTimeUnixNano = startTimeUnixNano;
        this.endTimeUnixNano = endTimeUnixNano;
        this.statusMessage = statusMessage;
        this.statusCode = statusCode;
    }

    public byte[] getSpanId() {
        return spanId;
    }

    public void setSpanId(byte[] spanId) {
        this.spanId = spanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public io.opentelemetry.proto.trace.v1.Span.SpanKind getKind() {
        return kind;
    }

    public void setKind(io.opentelemetry.proto.trace.v1.Span.SpanKind kind) {
        this.kind = kind;
    }

    public Long getStartTimeUnixNano() {
        return startTimeUnixNano;
    }

    public void setStartTimeUnixNano(Long startTimeUnixNano) {
        this.startTimeUnixNano = startTimeUnixNano;
    }

    public Long getEndTimeUnixNano() {
        return endTimeUnixNano;
    }

    public void setEndTimeUnixNano(Long endTimeUnixNano) {
        this.endTimeUnixNano = endTimeUnixNano;
    }

    public Set<SpanAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<SpanAttribute> attributes) {
        this.attributes = attributes;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Link> getLinks() {
        return links;
    }

    public void setLinks(Set<Link> links) {
        this.links = links;
    }

    public Trace getTrace() {
        return trace;
    }

    public void setTrace(Trace trace) {
        this.trace = trace;
    }

    public Span getParent() {
        return parent;
    }

    public void setParent(Span parent) {
        this.parent = parent;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public io.opentelemetry.proto.trace.v1.Status.StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(io.opentelemetry.proto.trace.v1.Status.StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public Set<Span> getChildren() {
        return children;
    }

    public void setChildren(Set<Span> children) {
        this.children = children;
    }

    public Link getFromLink() {
        return fromLink;
    }

    public void setFromLink(Link fromLink) {
        this.fromLink = fromLink;
    }

    public Set<SpanResourceAttribute> getSpanResourceAttributes() {
        return spanResourceAttributes;
    }

    public void setSpanResourceAttributes(Set<SpanResourceAttribute> spanResourceAttributes) {
        this.spanResourceAttributes = spanResourceAttributes;
    }

    public Set<SpanScopeAttribute> getSpanScopeAttributes() {
        return spanScopeAttributes;
    }

    public void setSpanScopeAttributes(Set<SpanScopeAttribute> spanScopeAttributes) {
        this.spanScopeAttributes = spanScopeAttributes;
    }
}
