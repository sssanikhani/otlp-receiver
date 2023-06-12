package com.otlp.receiver.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

import java.util.Properties;

@SpringBootApplication
@ServletComponentScan(basePackages = { "com.otlp.receiver.controllers", "com.otlp.receiver.application", "com.otlp.receiver.models" })
@ComponentScan(basePackages = { "com.otlp.receiver.controllers", "com.otlp.receiver.application", "com.otlp.receiver.models" })
public class OTLPReceiverApp {
    public static void main(String... args) {
        Properties properties = System.getProperties();
        for (Object key: properties.keySet()) {
            System.out.println(key + ": " + properties.getProperty(key.toString()));
        }
        SpringApplication.run(OTLPReceiverApp.class, args);
    }
}
