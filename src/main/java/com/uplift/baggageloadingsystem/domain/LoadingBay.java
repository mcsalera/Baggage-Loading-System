package com.uplift.baggageloadingsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class LoadingBay {
    @Id @GeneratedValue(strategy=IDENTITY)
    private Integer id;
    private String via;
    private String destination;
    private String busCompany;
    private String loadingBayName;
    private String location;
    @JsonIgnore
    @OneToMany(mappedBy = "loadingBay", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<CounterStorage> counterStorages;
    @JsonIgnore
    @OneToMany (mappedBy = "loadingBay", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<Passenger> passengers;
}
