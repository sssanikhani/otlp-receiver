package com.otlp.receiver.models.common;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;
import java.util.Set;

@MappedSuperclass
public class AttributedModel<ATTR, T2 extends Serializable> extends AbstractPersistable<T2> {

    @OneToMany
    private Set<ATTR> attributes;
    private Long droppedAttributesCount = 0L;

}
