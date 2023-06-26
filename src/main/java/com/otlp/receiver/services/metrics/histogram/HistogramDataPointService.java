package com.otlp.receiver.services.metrics.histogram;

import com.otlp.receiver.models.metrics.histogram.Histogram;
import com.otlp.receiver.models.metrics.histogram.HistogramDataPoint;
import com.otlp.receiver.models.metrics.histogram.HistogramDataPointAttribute;
import com.otlp.receiver.utils.Jsonizer;
import com.otlp.receiver.utils.ListConvertor;
import com.otlp.receiver.utils.ObjectPersister;
import io.opentelemetry.proto.common.v1.KeyValue;

import java.util.ArrayList;
import java.util.List;

public class HistogramDataPointService {
    public static void persistBatch(List<io.opentelemetry.proto.metrics.v1.HistogramDataPoint> dataPointsM, Histogram histogram) {
        for (io.opentelemetry.proto.metrics.v1.HistogramDataPoint dp : dataPointsM)
            persist(dp, histogram);
    }

    private static void persist(io.opentelemetry.proto.metrics.v1.HistogramDataPoint dataPointM, Histogram histogram) {
        HistogramDataPoint dataPoint = new HistogramDataPoint(
                dataPointM.getTimeUnixNano(), dataPointM.getStartTimeUnixNano(), dataPointM.getFlags(),
                dataPointM.getCount(), dataPointM.getSum(), dataPointM.getMin(),
                dataPointM.getMax(), ListConvertor.toLongArray(dataPointM.getBucketCountsList()),
                ListConvertor.toDoubleArray(dataPointM.getExplicitBoundsList()), histogram);
        ObjectPersister.persist(dataPoint);
        persistAttributes(dataPointM.getAttributesList(), dataPoint);
    }

    private static void persistAttributes(List<KeyValue> attributesM, HistogramDataPoint dataPoint) {
        List<Object> attributes = new ArrayList<>();
        for (KeyValue attribute : attributesM)
            attributes.add(new HistogramDataPointAttribute(attribute.getKey(), Jsonizer.jsonize(attribute.getValue()), dataPoint));
        ObjectPersister.persistBatch(attributes);
    }
}
