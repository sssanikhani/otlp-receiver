package com.otlp.receiver.controllers;


import com.otlp.receiver.services.logs.LogsRequestService;
import com.otlp.receiver.utils.Exceptions;
import io.opentelemetry.proto.collector.logs.v1.ExportLogsServiceRequest;
import io.opentelemetry.proto.collector.logs.v1.ExportLogsServiceResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

@RestController
@RequestMapping("/v1/logs")
public class Logs {
    @PostMapping
    public ResponseEntity<ExportLogsServiceResponse> addLog() throws IOException {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        ExportLogsServiceRequest requestMessage = ExportLogsServiceRequest.parseFrom(request.getInputStream());

        ExportLogsServiceResponse responseMessage = null;
        try {
            responseMessage = LogsRequestService.processLogsRequest(requestMessage);
        } catch (Exceptions.InvalidArguments e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(responseMessage);
    }
}
