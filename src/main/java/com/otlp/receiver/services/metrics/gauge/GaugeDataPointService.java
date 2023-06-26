package com.otlp.receiver.services.metrics.gauge;

import com.otlp.receiver.models.metrics.gauge.Gauge;
import com.otlp.receiver.models.metrics.gauge.GaugeDataPoint;
import com.otlp.receiver.models.metrics.gauge.GaugeDataPointAttribute;
import com.otlp.receiver.utils.Jsonizer;
import com.otlp.receiver.utils.ObjectPersister;
import io.opentelemetry.proto.common.v1.KeyValue;

import java.util.ArrayList;
import java.util.List;

public class GaugeDataPointService {
    private static void persistAttributes(List<KeyValue> attributesM, GaugeDataPoint dataPoint) {
        List<Object> attributes = new ArrayList<>();
        for (KeyValue attribute : attributesM)
            attributes.add(new GaugeDataPointAttribute(attribute.getKey(), Jsonizer.jsonize(attribute.getValue()), dataPoint));
        ObjectPersister.persistBatch(attributes);
    }

    public static void persistBatch(List<io.opentelemetry.proto.metrics.v1.NumberDataPoint> dataPointsM, Gauge gauge) {
        for (io.opentelemetry.proto.metrics.v1.NumberDataPoint dp : dataPointsM)
            persist(dp, gauge);
    }

    private static void persist(io.opentelemetry.proto.metrics.v1.NumberDataPoint dataPointM, Gauge gauge) {
        GaugeDataPoint dataPoint = build(dataPointM, gauge);
        ObjectPersister.persist(dataPoint);
        persistAttributes(dataPointM.getAttributesList(), dataPoint);
    }

    private static GaugeDataPoint build(io.opentelemetry.proto.metrics.v1.NumberDataPoint dp, Gauge gauge) {
        switch (dp.getValueCase()) {
            case AS_INT -> {
                return new GaugeDataPoint(dp.getTimeUnixNano(), dp.getStartTimeUnixNano(), dp.getFlags(), dp.getValueCase(), null, dp.getAsInt(), gauge);
            }
            case AS_DOUBLE -> {
                return new GaugeDataPoint(dp.getTimeUnixNano(), dp.getStartTimeUnixNano(), dp.getFlags(), dp.getValueCase(), dp.getAsDouble(), null, gauge);
            }
            default -> {
                return new GaugeDataPoint(dp.getTimeUnixNano(), dp.getStartTimeUnixNano(), dp.getFlags(), dp.getValueCase(), null, null, gauge);
            }
        }
    }
}
