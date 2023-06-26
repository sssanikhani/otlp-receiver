package com.otlp.receiver.services.metrics.summary;

import com.otlp.receiver.models.metrics.summary.Summary;
import com.otlp.receiver.models.metrics.summary.SummaryDataPoint;
import com.otlp.receiver.models.metrics.summary.SummaryDataPointAttribute;
import com.otlp.receiver.utils.Jsonizer;
import com.otlp.receiver.utils.ListConvertor;
import com.otlp.receiver.utils.ObjectPersister;
import io.opentelemetry.proto.common.v1.KeyValue;

import java.util.ArrayList;
import java.util.List;

public class SummaryDataPointService {
    public static void persistBatch(List<io.opentelemetry.proto.metrics.v1.SummaryDataPoint> dataPointsM, Summary summary) {
        for (io.opentelemetry.proto.metrics.v1.SummaryDataPoint dp : dataPointsM)
            persist(dp, summary);
    }

    private static void persist(io.opentelemetry.proto.metrics.v1.SummaryDataPoint dp, Summary summary) {
        SummaryDataPoint dataPoint = build(dp, summary);
        ObjectPersister.persist(dataPoint);
        persistAttributes(dp.getAttributesList(), dataPoint);
    }

    private static void persistAttributes(List<KeyValue> attributesM, SummaryDataPoint dataPoint) {
        List<Object> attributes = new ArrayList<>();
        for (KeyValue attribute : attributesM)
            attributes.add(new SummaryDataPointAttribute(attribute.getKey(), Jsonizer.jsonize(attribute.getValue()), dataPoint));
        ObjectPersister.persistBatch(attributes);
    }

    private static SummaryDataPoint build(io.opentelemetry.proto.metrics.v1.SummaryDataPoint dp, Summary summary) {
        List<Double> quantiles = new ArrayList<>();
        List<Double> quantileValues = new ArrayList<>();
        for (io.opentelemetry.proto.metrics.v1.SummaryDataPoint.ValueAtQuantile valueAtQuantile : dp.getQuantileValuesList()) {
            quantiles.add(valueAtQuantile.getQuantile());
            quantileValues.add(valueAtQuantile.getValue());
        }
        return new SummaryDataPoint(
                dp.getTimeUnixNano(), dp.getStartTimeUnixNano(), dp.getFlags(), dp.getCount(),
                dp.getSum(), ListConvertor.toDoubleArray(quantiles), ListConvertor.toDoubleArray(quantileValues), summary);
    }
}
