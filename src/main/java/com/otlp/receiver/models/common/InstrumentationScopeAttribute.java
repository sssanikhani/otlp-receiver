package com.otlp.receiver.models.common;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"instrumentationscope_id", "key"}))
public class InstrumentationScopeAttribute extends BaseAttribute {
    @JoinColumn(nullable = false)
    @ManyToOne
    private InstrumentationScope instrumentationScope;

    public InstrumentationScopeAttribute(String key, String value, InstrumentationScope instrumentationScope) {
        super(key, value);
        this.instrumentationScope = instrumentationScope;
    }

    public InstrumentationScopeAttribute() {
    }

    public InstrumentationScope getInstrumentationScope() {
        return instrumentationScope;
    }

    public void setInstrumentationScope(InstrumentationScope instrumentationScope) {
        this.instrumentationScope = instrumentationScope;
    }
}
