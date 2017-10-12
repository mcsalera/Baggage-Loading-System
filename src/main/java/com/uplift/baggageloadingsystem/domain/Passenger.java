package com.uplift.baggageloadingsystem.domain;

import com.uplift.baggageloadingsystem.forms.PassengerForm;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Passenger {
    @Id @GeneratedValue(strategy=IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private BigDecimal fee;
    private Double baggageWeight;
    private String code;
    private String qrCodeUrl;
    @OneToMany(mappedBy = "passenger", cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<Baggage> baggages;
    @ManyToOne @JoinColumn(name = "bus_id")
    private Bus bus;

    public Passenger () {}

    public Passenger(PassengerForm form) {
        this.firstName = form.getFirstName();
        this.lastName = form.getLastName();
        this.baggageWeight = form.getBaggageWeight();
        this.fee = form.getFee();
        this.code = form.getCode();
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public BigDecimal getFee() { return fee; }

    public void setFee(BigDecimal fee) { this.fee = fee; }

    public Double getBaggageWeight() {
        return baggageWeight;
    }

    public void setBaggageWeight(Double baggageWeight) {
        this.baggageWeight = baggageWeight;
    }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public Collection<Baggage> getBaggages() { return baggages; }

    public void setBaggages(Collection<Baggage> baggages) { this.baggages = baggages; }

    public Bus getBus() { return bus; }

    public void setBus(Bus bus) { this.bus = bus; }

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
        final Passenger other = (Passenger) obj;
        return new EqualsBuilder().
                append(this.id, other.id).
                isEquals();
    }
}
