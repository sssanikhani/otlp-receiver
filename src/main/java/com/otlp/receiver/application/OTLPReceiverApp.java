package com.otlp.receiver.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = { "com.otlp.receiver.controllers", "com.otlp.receiver.application" })
@ComponentScan(basePackages = { "com.otlp.receiver.controllers", "com.otlp.receiver.application" })
public class OTLPReceiverApp {

    public static final JPAService jpaService = JPAService.getInstance();
    public static void main(String... args) {
        SpringApplication.run(OTLPReceiverApp.class, args);
    }
}
