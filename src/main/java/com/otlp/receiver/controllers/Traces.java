package com.otlp.receiver.controllers;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.otlp.receiver.utils.DataNormalizer;
import io.opentelemetry.proto.collector.trace.v1.ExportTraceServiceRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.codec.DecoderException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@RestController
@RequestMapping("/v1/traces")
public class Traces {
    @PostMapping(consumes = "application/x-protobuf")
    public Object addTrace(@RequestBody HashMap<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        // Replacing SpanID and TraceID Hex Values with Base64
//        String normalizedBody;
//        try {
//            normalizedBody = DataNormalizer.normalizeRequestBody(requestBody);
//        } catch (DecoderException e) {
//            throw new RuntimeException(e);
//        }
//
//        // Converting Body to Protobuf Messages
//        ExportTraceServiceRequest.Builder tracesRequestBuilder = ExportTraceServiceRequest.newBuilder();
//        try {
//            JsonFormat.parser().merge(normalizedBody, tracesRequestBuilder);
//        } catch (InvalidProtocolBufferException e) {
//            throw new RuntimeException(e);
//        }




        ExportTraceServiceRequest requestMessage = ExportTraceServiceRequest.parseFrom(request.getInputStream());
        System.out.println(requestMessage.getResourceSpansList().get(0).getResource().getAttributesList());


//        System.out.println(requestMessage.getResourceSpansCount());
//        System.out.println(requestMessage.getResourceSpans(0).getResource().getAttributes(0).getKey());
//        System.out.println(bytesToHex(requestMessage.getResourceSpans(0).getScopeSpans(0).getSpans(0).getTraceId().toByteArray()));
//        System.out.println(bytesToHex(requestMessage.getResourceSpans(0).getScopeSpans(0).getSpans(0).getSpanId().toByteArray()));
        return null;
    }
}
