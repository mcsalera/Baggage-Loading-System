package com.uplift.baggageloadingsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Data
public class BaggageCounter {
    @Id @GeneratedValue(strategy=IDENTITY)
    private Integer id;
    private Integer number;
    @JsonIgnore
    @OneToOne @JoinColumn(name="staff_id")
    private Staff staff;

    public Integer getStaffId() {
        return this.staff.getId();
    }
}
