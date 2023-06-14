package com.otlp.receiver.services;

import com.otlp.receiver.models.common.InstrumentationScope;
import com.otlp.receiver.models.common.InstrumentationScopeAttribute;
import com.otlp.receiver.utils.ObjectPersister;
import io.opentelemetry.proto.common.v1.KeyValue;

import java.util.List;

public class InstrumentationScopService {

    private static void persistAttributes(List<KeyValue> attributes, InstrumentationScope scope) {
        for (KeyValue attribute : attributes) {
            ObjectPersister.persist(
                new InstrumentationScopeAttribute(attribute.getKey(), attribute.getValue().getStringValue(), scope)
            );
        }
    }

    public static InstrumentationScope persistInstrumentationScope(io.opentelemetry.proto.common.v1.InstrumentationScope scopeM) {
        InstrumentationScope scope = new InstrumentationScope(scopeM.getName(), scopeM.getVersion());
        scope.setDroppedAttributesCount((long) scopeM.getDroppedAttributesCount());
        ObjectPersister.persist(scope);
        persistAttributes(scopeM.getAttributesList(), scope);

        return scope;
    }
}
