// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: opentelemetry/proto/collector/logs/v1/logs_service.proto

package io.opentelemetry.proto.collector.logs.v1;

public final class LogsServiceProto {
  private LogsServiceProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_opentelemetry_proto_collector_logs_v1_ExportLogsServiceRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_opentelemetry_proto_collector_logs_v1_ExportLogsServiceRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_opentelemetry_proto_collector_logs_v1_ExportLogsServiceResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_opentelemetry_proto_collector_logs_v1_ExportLogsServiceResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_opentelemetry_proto_collector_logs_v1_ExportLogsPartialSuccess_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_opentelemetry_proto_collector_logs_v1_ExportLogsPartialSuccess_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n8opentelemetry/proto/collector/logs/v1/" +
      "logs_service.proto\022%opentelemetry.proto." +
      "collector.logs.v1\032&opentelemetry/proto/l" +
      "ogs/v1/logs.proto\"\\\n\030ExportLogsServiceRe" +
      "quest\022@\n\rresource_logs\030\001 \003(\0132).opentelem" +
      "etry.proto.logs.v1.ResourceLogs\"u\n\031Expor" +
      "tLogsServiceResponse\022X\n\017partial_success\030" +
      "\001 \001(\0132?.opentelemetry.proto.collector.lo" +
      "gs.v1.ExportLogsPartialSuccess\"O\n\030Export" +
      "LogsPartialSuccess\022\034\n\024rejected_log_recor" +
      "ds\030\001 \001(\003\022\025\n\rerror_message\030\002 \001(\t2\235\001\n\013Logs" +
      "Service\022\215\001\n\006Export\022?.opentelemetry.proto" +
      ".collector.logs.v1.ExportLogsServiceRequ" +
      "est\032@.opentelemetry.proto.collector.logs" +
      ".v1.ExportLogsServiceResponse\"\000B\230\001\n(io.o" +
      "pentelemetry.proto.collector.logs.v1B\020Lo" +
      "gsServiceProtoP\001Z0go.opentelemetry.io/pr" +
      "oto/otlp/collector/logs/v1\252\002%OpenTelemet" +
      "ry.Proto.Collector.Logs.V1b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          io.opentelemetry.proto.logs.v1.LogsProto.getDescriptor(),
        });
    internal_static_opentelemetry_proto_collector_logs_v1_ExportLogsServiceRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_opentelemetry_proto_collector_logs_v1_ExportLogsServiceRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_opentelemetry_proto_collector_logs_v1_ExportLogsServiceRequest_descriptor,
        new java.lang.String[] { "ResourceLogs", });
    internal_static_opentelemetry_proto_collector_logs_v1_ExportLogsServiceResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_opentelemetry_proto_collector_logs_v1_ExportLogsServiceResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_opentelemetry_proto_collector_logs_v1_ExportLogsServiceResponse_descriptor,
        new java.lang.String[] { "PartialSuccess", });
    internal_static_opentelemetry_proto_collector_logs_v1_ExportLogsPartialSuccess_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_opentelemetry_proto_collector_logs_v1_ExportLogsPartialSuccess_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_opentelemetry_proto_collector_logs_v1_ExportLogsPartialSuccess_descriptor,
        new java.lang.String[] { "RejectedLogRecords", "ErrorMessage", });
    io.opentelemetry.proto.logs.v1.LogsProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
