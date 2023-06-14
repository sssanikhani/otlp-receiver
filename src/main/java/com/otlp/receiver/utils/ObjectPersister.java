package com.otlp.receiver.utils;

import com.otlp.receiver.application.OTLPReceiverApp;

public class ObjectPersister {
    public static void persist(Object o) {
        OTLPReceiverApp.jpaService.runInTransaction(entityManager -> {
            entityManager.persist(o);
            return null;
        });
    }
}
