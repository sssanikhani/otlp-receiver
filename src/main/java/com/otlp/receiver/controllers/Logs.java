package com.otlp.receiver.controllers;


import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.otlp.receiver.utils.DataNormalizer;
import io.opentelemetry.proto.collector.logs.v1.ExportLogsServiceRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.codec.DecoderException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/v1/logs")
public class Logs {
    @PostMapping
    public Object addLog(@RequestBody HashMap<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) {
        // Replacing SpanID and TraceID Hex Values with Base64
        String normalizedBody;
        try {
            normalizedBody = DataNormalizer.normalizeRequestBody(requestBody);
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }

        // Converting Body to Protobuf Messages
        ExportLogsServiceRequest.Builder logsRequestBuilder = ExportLogsServiceRequest.newBuilder();
        try {
            JsonFormat.parser().merge(normalizedBody, logsRequestBuilder);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
        ExportLogsServiceRequest requestMessage = logsRequestBuilder.build();

        // TODO: Save Messages to Database

        return null; // TODO: Respond Correctly
    }
}
