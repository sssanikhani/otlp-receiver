package com.otlp.receiver.services.traces;

import com.otlp.receiver.models.traces.Event;
import com.otlp.receiver.models.traces.EventAttribute;
import com.otlp.receiver.models.traces.Span;
import com.otlp.receiver.utils.Jsonizer;
import com.otlp.receiver.utils.ObjectPersister;
import io.opentelemetry.proto.common.v1.KeyValue;

import java.util.List;

public class EventService {
    private static void persistAttributes(List<KeyValue> attributes, Event event) {
        for (KeyValue attribute : attributes) {
            ObjectPersister.persist(new EventAttribute(attribute.getKey(), Jsonizer.jsonize(attribute.getValue()), event));
        }
    }

    private static void persistEvent(io.opentelemetry.proto.trace.v1.Span.Event eventM, Span span) {
        Event event = new Event(span, eventM.getTimeUnixNano(), eventM.getName());
        ObjectPersister.persist(event);
        persistAttributes(eventM.getAttributesList(), event);
    }

    public static void persistBatch(List<io.opentelemetry.proto.trace.v1.Span.Event> events, Span span) {
        for (io.opentelemetry.proto.trace.v1.Span.Event event : events) {
            persistEvent(event, span);
        }
    }
}
