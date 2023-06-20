//package com.otlp.receiver.models.metrics;
//
//import com.otlp.receiver.models.resources.Resource;
//import jakarta.persistence.Entity;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import org.springframework.data.jpa.domain.AbstractPersistable;
//
//import java.util.Set;
//
//@Entity
//public class ResourceMetric extends AbstractPersistable<Long> {
//    @JoinColumn(nullable = false)
//    @ManyToOne
//    private Resource resource;
//
//    @OneToMany(mappedBy = "resourceMetric")
//    private Set<ScopeMetric> scopeMetrics;
//
//    private String schemaUrl;
//
//}
