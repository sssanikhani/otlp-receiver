package com.otlp.receiver.services;

import com.otlp.receiver.models.resources.Resource;
import com.otlp.receiver.models.resources.ResourceAttribute;
import com.otlp.receiver.utils.ObjectPersister;
import io.opentelemetry.proto.common.v1.KeyValue;

import java.util.List;

public class ResourceService {
    private static void persistAttributes(List<KeyValue> attributes, Resource resource) {
        for (KeyValue keyValue : attributes) {
            ObjectPersister.persist(
                new ResourceAttribute(keyValue.getKey(), keyValue.getValue().getStringValue(), resource)
            );
        }
    }

    public static Resource persistResource(io.opentelemetry.proto.resource.v1.Resource resourceM) {
        Resource resource = new Resource();
        resource.setDroppedAttributesCount((long) resourceM.getDroppedAttributesCount());
        ObjectPersister.persist(resource);
        persistAttributes(resourceM.getAttributesList(), resource);
        return resource;
    }
}
