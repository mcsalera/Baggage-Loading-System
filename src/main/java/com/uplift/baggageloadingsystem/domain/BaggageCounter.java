package com.uplift.baggageloadingsystem.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class BaggageCounter {
    @Id @GeneratedValue(strategy=IDENTITY)
    private Integer id;
    private Integer number;
    private Integer staffId;
    @OneToMany(mappedBy = "baggage")
    private Collection<Baggage> baggages;
    @OneToOne(mappedBy = "baggageCounter")
    private Staff staff;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getNumber() { return number; }

    public void setNumber(Integer number) { this.number = number; }

    public Integer getStaffId() { return staffId; }

    public void setStaffId(Integer staffId) { this.staffId = staffId; }

    public Collection<Baggage> getBaggages() { return baggages; }

    public void setBaggages(Collection<Baggage> baggages) { this.baggages = baggages; }

    public Staff getStaff() { return staff; }

    public void setStaff(Staff staff) { this.staff = staff; }

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
