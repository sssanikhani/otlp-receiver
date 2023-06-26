package com.otlp.receiver.services.metrics.sum;

import com.otlp.receiver.models.metrics.sum.Sum;
import com.otlp.receiver.models.metrics.sum.SumDataPoint;
import com.otlp.receiver.models.metrics.sum.SumDataPointAttribute;
import com.otlp.receiver.utils.Jsonizer;
import com.otlp.receiver.utils.ObjectPersister;
import io.opentelemetry.proto.common.v1.KeyValue;

import java.util.ArrayList;
import java.util.List;

public class SumDataPointService {
    private static void persistAttributes(List<KeyValue> attributesM, SumDataPoint dataPoint) {
        List<Object> attributes = new ArrayList<>();
        for (KeyValue attribute : attributesM)
            attributes.add(new SumDataPointAttribute(attribute.getKey(), Jsonizer.jsonize(attribute.getValue()), dataPoint));
        ObjectPersister.persistBatch(attributes);
    }

    public static void persistBatch(List<io.opentelemetry.proto.metrics.v1.NumberDataPoint> dataPointsM, Sum sum) {
        for (io.opentelemetry.proto.metrics.v1.NumberDataPoint dp : dataPointsM)
            persist(dp, sum);
    }

    private  static void persist(io.opentelemetry.proto.metrics.v1.NumberDataPoint dataPointM, Sum sum) {
        SumDataPoint dataPoint = build(dataPointM, sum);
        ObjectPersister.persist(dataPoint);
        persistAttributes(dataPointM.getAttributesList(), dataPoint);
    }

    private static SumDataPoint build(io.opentelemetry.proto.metrics.v1.NumberDataPoint dp, Sum sum) {
        switch (dp.getValueCase()) {
            case AS_INT -> {
                return new SumDataPoint(dp.getTimeUnixNano(), dp.getStartTimeUnixNano(), dp.getFlags(), dp.getValueCase(), null, dp.getAsInt(), sum);
            }
            case AS_DOUBLE -> {
                return new SumDataPoint(dp.getTimeUnixNano(), dp.getStartTimeUnixNano(), dp.getFlags(), dp.getValueCase(), dp.getAsDouble(), null, sum);
            }
            default -> {
                return new SumDataPoint(dp.getTimeUnixNano(), dp.getStartTimeUnixNano(), dp.getFlags(), dp.getValueCase(), null, null, sum);
            }
        }
    }
}
