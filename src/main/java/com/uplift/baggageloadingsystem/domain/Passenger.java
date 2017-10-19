package com.uplift.baggageloadingsystem.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String contactNumber;
    @JsonIgnore
    @ManyToOne @JoinColumn (name = "loading_bay_id")
    private LoadingBay loadingBay;
    @JsonIgnore
    @OneToMany(mappedBy = "passenger", cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<Baggage> baggages;

    public Passenger () {}

    public void setForm(PassengerForm form) {
        this.firstName = form.getFirstName();
        this.lastName = form.getLastName();
        this.baggageWeight = form.getBaggageWeight();
        this.fee = form.getFee();
        this.code = form.getCode();
        this.qrCodeUrl = form.getPassengerQrCodeUrl();
        this.contactNumber = form.getContactNumber();
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public LoadingBay getLoadingBay() {
        return loadingBay;
    }

    public void setLoadingBay(LoadingBay loadingBay) {
        this.loadingBay = loadingBay;
    }

    public Integer getLoadingBayId() {
        return this.loadingBay.getId();
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
        final Passenger other = (Passenger) obj;
        return new EqualsBuilder().
                append(this.id, other.id).
                isEquals();
    }
}
