package com.otlp.receiver.services.logs;

import com.otlp.receiver.models.logs.LogRecord;
import com.otlp.receiver.models.logs.LogRecordAttribute;
import com.otlp.receiver.models.logs.LogResourceAttribute;
import com.otlp.receiver.models.logs.LogScopeAttribute;
import com.otlp.receiver.models.traces.Span;
import com.otlp.receiver.models.traces.Trace;
import com.otlp.receiver.services.traces.SpanService;
import com.otlp.receiver.services.traces.TraceService;
import com.otlp.receiver.utils.Jsonizer;
import com.otlp.receiver.utils.ObjectPersister;
import io.opentelemetry.proto.common.v1.KeyValue;

import java.util.List;

public class LogRecordService {
    private static void persistAttributes(List<KeyValue> attributes, LogRecord logRecord) {
        for (KeyValue attribute : attributes) {
            ObjectPersister.persist(new LogRecordAttribute(attribute.getKey(), Jsonizer.jsonize(attribute.getValue()), logRecord));
        }
    }

    private static void persistScopeAttributes(List<KeyValue> attributes, LogRecord logRecord) {
        for (KeyValue attr : attributes) {
            ObjectPersister.persist(new LogScopeAttribute(attr.getKey(), Jsonizer.jsonize(attr.getValue()), logRecord));
        }
    }

    private static void persistResourceAttributes(List<KeyValue> attributes, LogRecord logRecord) {
        for (KeyValue attr : attributes) {
            ObjectPersister.persist(new LogResourceAttribute(attr.getKey(), Jsonizer.jsonize(attr.getValue()), logRecord));
        }
    }

    public static void persistLogRecord(
            io.opentelemetry.proto.logs.v1.LogRecord logRecordM,
            io.opentelemetry.proto.logs.v1.ScopeLogs scopeLogM,
            io.opentelemetry.proto.logs.v1.ResourceLogs resourceLogM
    ) {
        Span span = SpanService.getSpan(logRecordM.getSpanId().toByteArray());
        Trace trace = TraceService.getTrace(logRecordM.getTraceId().toByteArray());

        LogRecord logRecord = new LogRecord(
                scopeLogM.getScope().getName(),
                scopeLogM.getScope().getVersion(),
                resourceLogM.getSchemaUrl(),
                scopeLogM.getSchemaUrl(),
                logRecordM.getSeverityNumber(),
                logRecordM.getTimeUnixNano(),
                logRecordM.getObservedTimeUnixNano(),
                logRecordM.getSeverityText(),
                Jsonizer.jsonize(logRecordM.getBody()),
                span,
                trace,
                logRecordM.getFlags()
        );
        ObjectPersister.persist(logRecord);
        persistAttributes(logRecordM.getAttributesList(), logRecord);
        persistScopeAttributes(scopeLogM.getScope().getAttributesList(), logRecord);
        persistResourceAttributes(resourceLogM.getResource().getAttributesList(), logRecord);
    }
}
