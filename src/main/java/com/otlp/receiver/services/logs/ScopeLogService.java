package com.otlp.receiver.services.logs;

import com.otlp.receiver.models.common.InstrumentationScope;
import com.otlp.receiver.models.logs.ResourceLog;
import com.otlp.receiver.models.logs.ScopeLog;
import com.otlp.receiver.services.InstrumentationScopService;
import com.otlp.receiver.utils.ObjectPersister;

import java.util.List;

public class ScopeLogService {
    private static void persistLogRecords(List<io.opentelemetry.proto.logs.v1.LogRecord> logRecords, ScopeLog scopeLog) {
        for (io.opentelemetry.proto.logs.v1.LogRecord logRecord : logRecords) {
            LogRecordService.persistLogRecord(logRecord, scopeLog);
        }
    }

    public static void persistScopeLog(io.opentelemetry.proto.logs.v1.ScopeLogs scopeLogM, ResourceLog resourceLog) {
        InstrumentationScope scope = InstrumentationScopService.persistInstrumentationScope(scopeLogM.getScope());
        ScopeLog scopeLog = new ScopeLog(scope, scopeLogM.getSchemaUrl(), resourceLog);
        ObjectPersister.persist(scopeLog);
        persistLogRecords(scopeLogM.getLogRecordsList(), scopeLog);
    }
}
