package com.uplift.baggageloadingsystem.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Baggage {
    @Id @GeneratedValue(strategy=IDENTITY)
    private Integer id;
    private String status = "NOT LOADED";
    private String qrCodeUrl;
    private String code;
    @JsonIgnore
    @ManyToOne @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public Passenger getPassenger() { return passenger; }

    public void setPassenger(Passenger passenger) { this.passenger = passenger; }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPassengerId() {
        return this.passenger.getId();
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
        final Baggage other = (Baggage) obj;
        return new EqualsBuilder().
                append(this.id, other.id).
                isEquals();
    }

}