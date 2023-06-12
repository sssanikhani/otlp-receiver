package com.otlp.receiver.models.logs;

import com.otlp.receiver.models.common.BaseKeyValue;
import jakarta.persistence.*;

@Entity
public class LogRecordAttribute extends BaseKeyValue {
    @Column(nullable = false)
    @ManyToOne
    private LogRecord logRecord;

    public LogRecord getLogRecord() {
        return logRecord;
    }

    public void setLogRecord(LogRecord logRecord) {
        this.logRecord = logRecord;
    }
}
