package com.otlp.receiver.models.resources;

import java.util.Set;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Resource extends AbstractPersistable<Long> {

    @OneToMany(mappedBy = "resource")
    private Set<ResourceAttribute> attributes;
    private long droppedAttributesCount = 0;

    public Set<ResourceAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<ResourceAttribute> attributes) {
        this.attributes = attributes;
    }

    public long getDroppedAttributesCount() {
        return droppedAttributesCount;
    }

    public void setDroppedAttributesCount(long droppedAttributesCount) {
        this.droppedAttributesCount = droppedAttributesCount;
    }
}
