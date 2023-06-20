package com.otlp.receiver.controllers;

import com.otlp.receiver.services.logs.LogService;
import com.otlp.receiver.services.traces.TraceService;
import com.otlp.receiver.utils.Exceptions;
import io.opentelemetry.proto.collector.logs.v1.ExportLogsServiceResponse;
import io.opentelemetry.proto.collector.trace.v1.ExportTraceServiceRequest;
import io.opentelemetry.proto.collector.trace.v1.ExportTraceServiceResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/v1/traces")
public class Traces {
    @PostMapping
    public ResponseEntity<ExportTraceServiceResponse> addTrace() throws IOException {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        ExportTraceServiceRequest requestMessage = ExportTraceServiceRequest.parseFrom(request.getInputStream());

        ExportTraceServiceResponse responseMessage = null;
        try {
            responseMessage = TraceService.processTraceRequest(requestMessage);
        } catch (Exceptions.InvalidArguments e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(responseMessage);
    }
}
