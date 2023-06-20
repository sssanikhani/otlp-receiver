package com.otlp.receiver.models.traces;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Set;

@Entity
public class Event extends AbstractPersistable<Long> {
    @JoinColumn(nullable = false)
    @ManyToOne
    private Span span;

    private Long timeUnixNano;
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "parent")
    private Set<EventAttribute> attributes;

    public Event() {
    }

    public Event(Span span, Long timeUnixNano, String name) {
        this.span = span;
        this.timeUnixNano = timeUnixNano;
        this.name = name;
    }

    public Span getSpan() {
        return span;
    }

    public void setSpan(Span span) {
        this.span = span;
    }

    public Long getTimeUnixNano() {
        return timeUnixNano;
    }

    public void setTimeUnixNano(Long timeUnixNano) {
        this.timeUnixNano = timeUnixNano;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EventAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<EventAttribute> attributes) {
        this.attributes = attributes;
    }
}
