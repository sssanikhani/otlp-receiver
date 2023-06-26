package com.otlp.receiver.services.metrics.exponential_histogram;

import com.otlp.receiver.models.metrics.exponential_histogram.ExponentialHistogram;
import com.otlp.receiver.models.metrics.exponential_histogram.ExponentialHistogramDataPoint;
import com.otlp.receiver.models.metrics.exponential_histogram.ExponentialHistogramDataPointAttribute;
import com.otlp.receiver.models.metrics.histogram.HistogramDataPointAttribute;
import com.otlp.receiver.utils.Jsonizer;
import com.otlp.receiver.utils.ListConvertor;
import com.otlp.receiver.utils.ObjectPersister;
import io.opentelemetry.proto.common.v1.KeyValue;

import java.util.ArrayList;
import java.util.List;

public class ExponentialHistogramDataPointService {
    public static void persistBatch(List<io.opentelemetry.proto.metrics.v1.ExponentialHistogramDataPoint> dataPointsM, ExponentialHistogram exponentialHistogram) {
        for (io.opentelemetry.proto.metrics.v1.ExponentialHistogramDataPoint dp : dataPointsM)
            persist(dp, exponentialHistogram);
    }

    private static void persist(io.opentelemetry.proto.metrics.v1.ExponentialHistogramDataPoint dataPointM, ExponentialHistogram exponentialHistogram) {
        ExponentialHistogramDataPoint dataPoint = new ExponentialHistogramDataPoint(
                dataPointM.getTimeUnixNano(), dataPointM.getStartTimeUnixNano(), dataPointM.getFlags(),
                dataPointM.getCount(), dataPointM.getSum(), dataPointM.getMin(), dataPointM.getMax(),
                dataPointM.getScale(), dataPointM.getZeroCount(), dataPointM.getZeroThreshold(),
                dataPointM.getPositive().getOffset(),
                ListConvertor.toLongArray(dataPointM.getPositive().getBucketCountsList()),
                dataPointM.getNegative().getOffset(),
                ListConvertor.toLongArray(dataPointM.getNegative().getBucketCountsList()), exponentialHistogram);
        ObjectPersister.persist(dataPoint);
        persistAttributes(dataPointM.getAttributesList(), dataPoint);
    }

    private static void persistAttributes(List<KeyValue> attributesM, ExponentialHistogramDataPoint dataPoint) {
        List<Object> attributes = new ArrayList<>();
        for (KeyValue attribute : attributesM)
            attributes.add(new ExponentialHistogramDataPointAttribute(attribute.getKey(), Jsonizer.jsonize(attribute.getValue()), dataPoint));
        ObjectPersister.persistBatch(attributes);
    }
}
