package com.otlp.receiver.models.logs;

import com.otlp.receiver.models.common.BaseAttribute;
import jakarta.persistence.*;

@Entity
public class LogRecordAttribute extends BaseAttribute {
    @JoinColumn(nullable = false)
    @ManyToOne
    private LogRecord logRecord;

    public LogRecordAttribute(String key, String value, LogRecord logRecord) {
        super(key, value);
        this.logRecord = logRecord;
    }

    public LogRecordAttribute() {
    }

    public LogRecord getLogRecord() {
        return logRecord;
    }

    public void setLogRecord(LogRecord logRecord) {
        this.logRecord = logRecord;
    }
}
