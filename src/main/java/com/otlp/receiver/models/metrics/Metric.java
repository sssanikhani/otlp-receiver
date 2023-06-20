//package com.otlp.receiver.models.metrics;
//
//import jakarta.persistence.*;
//import org.springframework.data.jpa.domain.AbstractPersistable;
//
//@Entity
//public class Metric extends AbstractPersistable<Long> {
//    @JoinColumn(nullable = false)
//    @ManyToOne
//    private ScopeMetric scopeMetric;
//
//    @Column(unique = true)
//    private String name;
//    private String description;
//    private String unit;
//
////    @OneToOne
////    private Guage guage;
////
////    @OneToOne
////    private Sum sum;
////
////    @OneToOne
////    private Histogram histogram;
////
////    @OneToOne
////    private ExponentialHistogram exponentialHistogram;
////
////    @OneToOne
////    private Summary summary;
//
//}
