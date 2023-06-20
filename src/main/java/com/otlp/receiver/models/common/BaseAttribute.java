package com.otlp.receiver.models.common;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Objects;


@MappedSuperclass
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"parent_id", "key"}))
public class BaseAttribute<T> extends AbstractPersistable<Long> {
    @Column(nullable = false)
    private String key;
    @Column(nullable = false)
    private String value;

    @JoinColumn(nullable = false)
    @ManyToOne
    private T parent;

    public BaseAttribute(String key, String value, T parent) {
        this.key = key;
        this.value = value;
        this.parent = parent;
    }

    public BaseAttribute() {

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public T getParent() {
        return parent;
    }

    public void setParent(T parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BaseAttribute<?> that = (BaseAttribute<?>) o;
        return key.equals(that.key) && Objects.equals(value, that.value) && parent.equals(that.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
