package com.otlp.receiver.models.logs;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.*;

@Entity
public class LogScopeAttribute extends BaseAttribute<LogRecord> {
    public LogScopeAttribute(String key, String value, LogRecord parent) {
        super(key, value, parent);
    }

    public LogScopeAttribute() {

    }
}
