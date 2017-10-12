package com.uplift.baggageloadingsystem.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Baggage {
    @Id @GeneratedValue(strategy=IDENTITY)
    private Integer id;
    private Integer passengerId;
    private Integer baggageCounterId;
    private String status;
    private BigDecimal weight;
    @ManyToOne @JoinColumn(name = "passenger_id")
    private Passenger passenger;
    @ManyToOne @JoinColumn(name = "baggage_counter_id")
    private BaggageCounter baggageCounter;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getPassengerId() { return passengerId; }

    public void setPassengerId(Integer passengerId) { this.passengerId = passengerId; }

    public Integer getBaggageCounterId() { return baggageCounterId; }

    public void setBaggageCounterId(Integer baggageCounterId) { this.baggageCounterId = baggageCounterId; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public BigDecimal getWeight() { return weight; }

    public void setWeight(BigDecimal weight) { this.weight = weight; }

    public Passenger getPassenger() { return passenger; }

    public void setPassenger(Passenger passenger) { this.passenger = passenger; }

    public BaggageCounter getBaggageCounter() { return baggageCounter; }

    public void setBaggageCounter(BaggageCounter baggageCounter) { this.baggageCounter = baggageCounter; }

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
        final Baggage other = (Baggage) obj;
        return new EqualsBuilder().
                append(this.id, other.id).
                isEquals();
    }

}
