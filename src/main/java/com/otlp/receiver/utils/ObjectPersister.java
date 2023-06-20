package com.otlp.receiver.utils;

import com.otlp.receiver.application.OTLPReceiverApp;

import java.util.List;

public class ObjectPersister {
    public static void persist(Object o) {
        OTLPReceiverApp.jpaService.runInTransaction(entityManager -> {
            entityManager.persist(o);
            return null;
        });
    }

    public static void persistBatch(List<Object> objects) {
        OTLPReceiverApp.jpaService.runInTransaction(entityManager -> {
           for (Object o : objects)
               entityManager.persist(o);
           return null;
        });
    }
}
