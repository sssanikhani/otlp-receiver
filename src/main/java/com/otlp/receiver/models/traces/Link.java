package com.otlp.receiver.models.traces;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Set;

@Entity
public class Link extends AbstractPersistable<Long> {
    @OneToOne(mappedBy = "fromLink")
    private Span to;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Span from;

    @OneToMany(mappedBy = "parent")
    private Set<LinkAttribute> attributes;

    public Link() {
    }

    public Link(Span to, Span from) {
        this.to = to;
        this.from = from;
    }

    public Set<LinkAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<LinkAttribute> attributes) {
        this.attributes = attributes;
    }

    public Span getTo() {
        return to;
    }

    public void setTo(Span to) {
        this.to = to;
    }

    public Span getFrom() {
        return from;
    }

    public void setFrom(Span from) {
        this.from = from;
    }
}
