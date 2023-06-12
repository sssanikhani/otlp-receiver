package com.otlp.receiver.models.common;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class InstrumentationScopeAttribute extends BaseKeyValue{
    @ManyToOne
    private InstrumentationScope instrumentationScope;

    public InstrumentationScope getInstrumentationScope() {
        return instrumentationScope;
    }

    public void setInstrumentationScope(InstrumentationScope instrumentationScope) {
        this.instrumentationScope = instrumentationScope;
    }
}
