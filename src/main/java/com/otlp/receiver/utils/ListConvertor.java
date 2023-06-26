package com.otlp.receiver.utils;

import java.util.List;

public class ListConvertor {
    public static long[] toLongArray(List<Long> l) {
        long[] arr = new long[l.size()];
        for (int i = 0; i < l.size(); i++)
            arr[i] = l.get(i);
        return arr;
    }

    public static double[] toDoubleArray(List<Double> l) {
        double[] arr = new double[l.size()];
        for (int i = 0; i < l.size(); i++)
            arr[i] = l.get(i);
        return arr;
    }
}
