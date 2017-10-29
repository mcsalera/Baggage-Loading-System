package com.uplift.baggageloadingsystem.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Baggage {
    @Id @GeneratedValue(strategy=IDENTITY)
    private Integer id;
    private String status = "NOT LOADED";
    private String qrCodeUrl;
    private String code;
    @JsonIgnore
    @ManyToOne @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    public Integer getPassengerId() {
        return this.passenger.getId();
    }
}