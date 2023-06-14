package com.otlp.receiver.controllers;


import com.google.gson.Gson;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.otlp.receiver.services.logs.ResourceLogService;
import com.otlp.receiver.utils.DataNormalizer;
import com.otlp.receiver.utils.Exceptions;
import com.otlp.receiver.utils.Responses;
import io.opentelemetry.proto.collector.logs.v1.ExportLogsServiceRequest;
import io.opentelemetry.proto.collector.logs.v1.ExportLogsServiceResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.codec.DecoderException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/v1/logs")
public class Logs {
    @PostMapping
    public HashMap<String,Object> addLog(@RequestBody HashMap<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws InvalidProtocolBufferException {
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
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new Responses.BadRequest(e.getMessage()).toHashMap();
        }
        ExportLogsServiceRequest requestMessage = logsRequestBuilder.build();

        ExportLogsServiceResponse responseMessage = null;
        try {
            responseMessage = ResourceLogService.processLogsRequest(requestMessage);
        } catch (Exceptions.InvalidArguments e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new Responses.BadRequest(e.getMessage()).toHashMap();
        }

        return (HashMap<String, Object>) new Gson().fromJson(JsonFormat.printer().print(responseMessage), HashMap.class);
    }
}
