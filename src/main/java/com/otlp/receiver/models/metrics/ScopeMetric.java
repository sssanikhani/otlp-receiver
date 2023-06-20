//package com.otlp.receiver.models.metrics;
//
//import com.otlp.receiver.models.common.InstrumentationScope;
//import jakarta.persistence.Entity;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import org.springframework.data.jpa.domain.AbstractPersistable;
//
//import java.util.Set;
//
//@Entity
//public class ScopeMetric extends AbstractPersistable<Long> {
//    @JoinColumn(nullable = false)
//    @ManyToOne
//    private ResourceMetric resourceMetric;
//
//    @ManyToOne
//    private InstrumentationScope scope;
//
//    @OneToMany(mappedBy = "scopeMetric")
//    private Set<Metric> metrics;
//
//    private String schemaUrl;
//
//}
