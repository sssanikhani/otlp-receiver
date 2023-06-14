package com.otlp.receiver.services.logs;

import com.otlp.receiver.models.logs.LogRecord;
import com.otlp.receiver.models.logs.LogRecordAttribute;
import com.otlp.receiver.models.logs.ScopeLog;
import com.otlp.receiver.utils.ObjectPersister;
import io.opentelemetry.proto.common.v1.KeyValue;

import java.util.List;

public class LogRecordService {
    private static void persistAttributes(List<KeyValue> attributes, LogRecord logRecord) {
        for (KeyValue attribute : attributes) {
            ObjectPersister.persist(new LogRecordAttribute(attribute.getKey(), attribute.getValue().getStringValue(), logRecord));
        }
    }

    public static void persistLogRecord(io.opentelemetry.proto.logs.v1.LogRecord logRecordM, ScopeLog scopeLog) {
        LogRecord logRecord = new LogRecord(logRecordM.getSeverityNumber(), logRecordM.getTimeUnixNano(),
                logRecordM.getObservedTimeUnixNano(), logRecordM.getSeverityText(),
                logRecordM.getBody().getStringValue(), (long) logRecordM.getDroppedAttributesCount(),
                logRecordM.getFlags(), logRecordM.getTraceId().toByteArray(), logRecordM.getSpanId().toByteArray(), scopeLog);
        ObjectPersister.persist(logRecord);
        persistAttributes(logRecordM.getAttributesList(), logRecord);
    }
}
