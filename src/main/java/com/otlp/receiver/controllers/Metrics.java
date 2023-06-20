package com.otlp.receiver.controllers;


import io.opentelemetry.proto.collector.metrics.v1.ExportMetricsServiceRequest;
import io.opentelemetry.proto.collector.metrics.v1.ExportMetricsServiceResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

@RestController
@RequestMapping("/v1/metrics")
public class Metrics {
    @PostMapping
    public Object addMetric() throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        ExportMetricsServiceRequest requestMessage = ExportMetricsServiceRequest.parseFrom(request.getInputStream());

        ExportMetricsServiceResponse responseMessage = null;
//        try {
//            responseMessage = MetricService.processMetricRequest(requestMessage);
//        } catch (Exceptions.InvalidArguments e) {
//            return ResponseEntity.badRequest().build();
//        }

        return ResponseEntity.ok(responseMessage);
    }
}
