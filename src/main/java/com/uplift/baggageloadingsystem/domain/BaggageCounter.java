package com.uplift.baggageloadingsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class BaggageCounter {
    @Id @GeneratedValue(strategy=IDENTITY)
    private Integer id;
    private Integer number;
    @JsonIgnore
    @OneToOne @JoinColumn(name="staff_id")
    private Staff staff;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getNumber() { return number; }

    public void setNumber(Integer number) { this.number = number; }

    public Staff getStaff() { return staff; }

    public void setStaff(Staff staff) { this.staff = staff; }

    public Integer getStaffId() {
        return this.staff.getId();
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
        final BaggageCounter other = (BaggageCounter) obj;
        return new EqualsBuilder().
                append(this.id, other.id).
                isEquals();
    }
}
