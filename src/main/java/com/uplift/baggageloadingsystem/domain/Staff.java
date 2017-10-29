package com.uplift.baggageloadingsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class Staff {
    @Id @GeneratedValue(strategy=IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    @JsonIgnore
    @OneToOne(mappedBy = "staff", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private BaggageCounter baggageCounter;

}
