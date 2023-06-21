package com.otlp.receiver.models.logs;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.Entity;

@Entity
public class LogRecordAttribute extends BaseAttribute<LogRecord> {
    public LogRecordAttribute(String key, String value, LogRecord parent) {
        super(key, value, parent);
    }

    public LogRecordAttribute() {
    }
}
