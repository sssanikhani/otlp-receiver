package com.otlp.receiver.services.traces;

import com.otlp.receiver.application.OTLPReceiverApp;
import com.otlp.receiver.models.traces.*;
import com.otlp.receiver.utils.Exceptions;
import com.otlp.receiver.utils.Jsonizer;
import com.otlp.receiver.utils.ObjectPersister;
import io.opentelemetry.proto.common.v1.KeyValue;

import java.util.ArrayList;
import java.util.List;

public class SpanService {
    public static Span getSpan(byte[] spanId) {
        try {
            return OTLPReceiverApp.jpaService.runInTransaction(entityManager -> entityManager.createQuery(
                            "select s from Span s where s.spanId = :id", Span.class
                    ).setParameter("id", spanId).getSingleResult()
            );
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }

    private static void persistAttributes(List<KeyValue> attributesM, Span span) {
        List<Object> attributes = new ArrayList<>();
        for (KeyValue attribute : attributesM) {
            attributes.add(new SpanAttribute(attribute.getKey(), Jsonizer.jsonize(attribute.getValue()), span));
        }
        ObjectPersister.persistBatch(attributes);
    }

    private static void persistScopeAttributes(List<KeyValue> attributesM, Span span) {
        List<Object> attributes = new ArrayList<>();
        for (KeyValue attr : attributesM) {
            attributes.add(new SpanScopeAttribute(attr.getKey(), Jsonizer.jsonize(attr.getValue()), span));
        }
        ObjectPersister.persistBatch(attributes);
    }

    private static void persistResourceAttributes(List<KeyValue> attributesM, Span span) {
        List<Object> attributes = new ArrayList<>();
        for (KeyValue attr : attributesM) {
            attributes.add(new SpanResourceAttribute(attr.getKey(), Jsonizer.jsonize(attr.getValue()), span));
        }
        ObjectPersister.persistBatch(attributes);
    }

    public static Span persistSpan(io.opentelemetry.proto.trace.v1.Span spanM, io.opentelemetry.proto.trace.v1.ScopeSpans scopeSpanM, io.opentelemetry.proto.trace.v1.ResourceSpans resourceSpanM) throws Exceptions.ValidationError {
        Trace trace = TraceService.getOrCreateTrace(spanM.getTraceId().toByteArray(), spanM.getTraceState());
        Span parent = null;
        if (!spanM.getParentSpanId().isEmpty()) {
            parent = getSpan(spanM.getParentSpanId().toByteArray());
            if (parent == null)
                throw new Exceptions.ValidationError(String.format("parent span %s not found for span with id %s", spanM.getParentSpanId(), spanM.getSpanId()));
        }
        Span span = new Span(scopeSpanM.getScope().getName(), scopeSpanM.getScope().getVersion(),
                resourceSpanM.getSchemaUrl(), scopeSpanM.getSchemaUrl(), trace, spanM.getSpanId().toByteArray(),
                parent, spanM.getName(), spanM.getKind(), spanM.getStartTimeUnixNano(), spanM.getEndTimeUnixNano(),
                spanM.getStatus().getMessage(), spanM.getStatus().getCode());
        ObjectPersister.persist(span);

        persistAttributes(spanM.getAttributesList(), span);
        persistScopeAttributes(scopeSpanM.getScope().getAttributesList(), span);
        persistResourceAttributes(resourceSpanM.getResource().getAttributesList(), span);

        LinkService.persistBatch(spanM.getLinksList(), span);
        EventService.persistBatch(spanM.getEventsList(), span);

        return span;
    }
}
