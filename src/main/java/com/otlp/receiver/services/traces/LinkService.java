package com.otlp.receiver.services.traces;

import com.otlp.receiver.models.traces.Link;
import com.otlp.receiver.models.traces.LinkAttribute;
import com.otlp.receiver.models.traces.Span;
import com.otlp.receiver.utils.Exceptions;
import com.otlp.receiver.utils.Jsonizer;
import com.otlp.receiver.utils.ObjectPersister;
import io.opentelemetry.proto.common.v1.KeyValue;

import java.util.List;

public class LinkService {
    private static void persistAttributes(List<KeyValue> attributes, Link link) {
        for (KeyValue attribute : attributes) {
            ObjectPersister.persist(new LinkAttribute(attribute.getKey(), Jsonizer.jsonize(attribute.getValue()), link));
        }
    }

    private static void persistLink(io.opentelemetry.proto.trace.v1.Span.Link linkM, Span from) throws Exceptions.ValidationError {
        Span to = SpanService.getSpan(linkM.getSpanId().toByteArray());
        if (to == null)
            throw new Exceptions.ValidationError(String.format("Could not create link. target span with id %s not found.", linkM.getSpanId()));

        Link link = new Link(to, from);
        ObjectPersister.persist(link);
        persistAttributes(linkM.getAttributesList(), link);
    }

    public static void persistBatch(List<io.opentelemetry.proto.trace.v1.Span.Link> links, Span span) throws Exceptions.ValidationError {
        for (io.opentelemetry.proto.trace.v1.Span.Link link : links) {
            persistLink(link, span);
        }
    }
}
