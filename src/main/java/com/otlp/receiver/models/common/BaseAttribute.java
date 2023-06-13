package com.otlp.receiver.models.common;

import jakarta.persistence.*;

import java.util.Objects;


@MappedSuperclass
public class BaseAttribute {
    @Id
    @Column(nullable = false, unique = true)
    String key;
    @Column(nullable = false)
    String value;

    public BaseAttribute(String key, String value) {
        this.key = key;
        this.value = value;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseAttribute that = (BaseAttribute) o;
        return key.equals(that.key) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
