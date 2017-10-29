package com.uplift.baggageloadingsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class CounterStorage {
    @Id @GeneratedValue(strategy=IDENTITY)
    private Integer id;
    @JsonIgnore
    @ManyToOne @JoinColumn(name = "counter_id")
    private BaggageCounter baggageCounter;
    @JsonIgnore
    @ManyToOne @JoinColumn(name = "loading_bay_id")
    private LoadingBay loadingBay;

    public Integer getLoadingBayId() {
        return this.loadingBay.getId();
    }

    public Integer getBaggageCounterId() {
        return this.baggageCounter.getId();
    }
}