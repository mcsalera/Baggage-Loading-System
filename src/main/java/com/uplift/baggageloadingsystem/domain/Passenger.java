package com.uplift.baggageloadingsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

import static javax.persistence.GenerationType.IDENTITY;

@Builder
@Data
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
    @Singular
    @JsonIgnore
    @OneToMany(mappedBy = "passenger", cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<Baggage> baggages;

    public Passenger () {}

    public Integer getLoadingBayId() {
        return this.loadingBay.getId();
    }
}
