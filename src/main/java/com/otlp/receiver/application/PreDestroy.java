package com.otlp.receiver.application;

import org.springframework.stereotype.Component;

@Component
public class PreDestroy {
    @jakarta.annotation.PreDestroy
    public void destroy() {
        OTLPReceiverApp.jpaService.shutdown();
    }
}
