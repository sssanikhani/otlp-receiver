package com.otlp.receiver.models.logs;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.*;

@Entity
public class LogResourceAttribute extends BaseAttribute<LogRecord> {
    public LogResourceAttribute(String key, String value, LogRecord parent) {
        super(key, value, parent);
    }

    public LogResourceAttribute() {
    }
}
