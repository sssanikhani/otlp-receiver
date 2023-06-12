package com.otlp.receiver.models.common;

import jakarta.persistence.*;


@MappedSuperclass
public class BaseKeyValue {
    @Id
    @Column(nullable = false, unique = true)
    String key;
    @Column(nullable = false)
    String value;

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
}
