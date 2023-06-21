package com.otlp.receiver.models.metrics;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Set;

@MappedSuperclass
public class BaseDataPoint extends AbstractPersistable<Long> {
    @Column(nullable = false)
    private Long timeUnixNano = 0L;
    private Long startTimeUnixNano;

    private Long flags;

    @OneToMany
    private Set<Exemplar> exemplars;

    public BaseDataPoint() {
    }

    public BaseDataPoint(Long timeUnixNano, Long startTimeUnixNano, Long flags) {
        this.timeUnixNano = timeUnixNano;
        this.startTimeUnixNano = startTimeUnixNano;
        this.flags = flags;
    }
}
