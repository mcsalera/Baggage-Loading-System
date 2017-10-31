package com.uplift.baggageloadingsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uplift.baggageloadingsystem.forms.PassengerForm;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor @AllArgsConstructor
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
    private String status = "NOT BOARDED";
    @JsonIgnore
    @ManyToOne @JoinColumn (name = "loading_bay_id")
    private LoadingBay loadingBay;
    @Singular
    @JsonIgnore
    @OneToMany(mappedBy = "passenger", cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<Baggage> baggages;

    public Integer getLoadingBayId() {
        return this.loadingBay.getId();
    }
}
