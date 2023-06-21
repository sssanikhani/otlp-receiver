package com.otlp.receiver.services.traces;

import com.otlp.receiver.application.OTLPReceiverApp;
import com.otlp.receiver.models.traces.Trace;
import com.otlp.receiver.utils.ObjectPersister;

public class TraceService {
    public static Trace getTrace(byte[] traceId) {
        try {
            return OTLPReceiverApp.jpaService.runInTransaction(entityManager -> entityManager.createQuery(
                    "select t from Trace t where t.traceId = :id", Trace.class
            ).setParameter("id", traceId).getSingleResult());
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }

    public static Trace getOrCreateTrace(byte[] traceId, String state) {
        Trace trace = getTrace(traceId);
        if (trace == null) {
            trace = new Trace(traceId, state);
            ObjectPersister.persist(trace);
        }
        return trace;
    }

    public static Trace persistTrace(byte[] traceId, String state) {
        Trace trace = new Trace(traceId, state);
        ObjectPersister.persist(trace);
        return trace;
    }
}
