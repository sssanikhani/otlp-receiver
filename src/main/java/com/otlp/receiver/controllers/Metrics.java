package com.otlp.receiver.controllers;


import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.otlp.receiver.utils.DataNormalizer;
import io.opentelemetry.proto.collector.metrics.v1.ExportMetricsServiceRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.codec.DecoderException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/v1/metrics", consumes = { "application/x-protobuf", "application/json" })
public class Metrics {
    @PostMapping(value = "")
    public Object addMetric(@RequestBody HashMap<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws InvalidProtocolBufferException {
        // Replacing SpanID and TraceID Hex Values with Base64
        String normalizedBody;
        try {
            normalizedBody = DataNormalizer.normalizeRequestBody(requestBody);
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }

        // Converting Body to Protobuf Messages
        ExportMetricsServiceRequest.Builder logsRequestBuilder = ExportMetricsServiceRequest.newBuilder();
        try {
            JsonFormat.parser().merge(normalizedBody, logsRequestBuilder);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
        ExportMetricsServiceRequest requestMessage = logsRequestBuilder.build();

        // TODO: Save Messages Data to Database

        return null; // TODO: Respond Correctly
    }
}
