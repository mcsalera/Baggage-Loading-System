package com.uplift.baggageloadingsystem.forms;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class PassengerForm {
    private Integer id;
    private String firstName;
    private String lastName;
    private Double baggageWeight;
    private Integer loadingBayId;
    private Integer baggageCount;
    private String contactNumber;
    private BigDecimal fee;
    private String code;
    private String passengerQrCodeUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getBaggageWeight() {
        return baggageWeight;
    }

    public void setBaggageWeight(Double baggageWeight) {
        this.baggageWeight = baggageWeight;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassengerQrCodeUrl() {
        return passengerQrCodeUrl;
    }

    public void setPassengerQrCodeUrl(String passengerQrCodeUrl) {
        this.passengerQrCodeUrl = passengerQrCodeUrl;
    }

    public Integer getBaggageCount() {
        return baggageCount;
    }

    public void setBaggageCount(Integer baggageCount) {
        this.baggageCount = baggageCount;
    }

    public Integer getLoadingBayId() {
        return loadingBayId;
    }

    public void setLoadingBayId(Integer loadingBayId) {
        this.loadingBayId = loadingBayId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
