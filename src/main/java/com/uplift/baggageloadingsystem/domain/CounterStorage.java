package com.uplift.baggageloadingsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

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

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public BaggageCounter getBaggageCounter() {
        return baggageCounter;
    }

    public void setBaggageCounter(BaggageCounter baggageCounter) {
        this.baggageCounter = baggageCounter;
    }

    public LoadingBay getLoadingBay() { return loadingBay; }

    public void setLoadingBay(LoadingBay loadingBay) { this.loadingBay = loadingBay; }

    public Integer getLoadingBayId() {
        return this.loadingBay.getId();
    }

    public Integer getBaggageCounterId() {
        return this.baggageCounter.getId();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
                // if deriving: appendSuper(super.hashCode()).
                        append(this.id).
                        toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CounterStorage other = (CounterStorage) obj;
        return new EqualsBuilder().
                append(this.id, other.id).
                isEquals();
    }
}
