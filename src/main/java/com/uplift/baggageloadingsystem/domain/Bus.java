package com.uplift.baggageloadingsystem.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Bus {
    @Id @GeneratedValue(strategy=IDENTITY)
    private Integer id;
    private String busCompany;
    @ManyToOne @JoinColumn(name = "loading_bay_id")
    private LoadingBay loadingBay;
    @OneToMany(mappedBy = "bus", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Collection<Passenger> passengers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusCompany() { return busCompany; }

    public void setBusCompany(String busCompany) { this.busCompany = busCompany; }

    public LoadingBay getLoadingBay() { return loadingBay; }

    public void setLoadingBay(LoadingBay loadingBay) { this.loadingBay = loadingBay; }

    public Collection<Passenger> getPassengers() { return passengers; }

    public void setPassengers(Collection<Passenger> passengers) { this.passengers = passengers; }

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
        final Bus other = (Bus) obj;
        return new EqualsBuilder().
                append(this.id, other.id).
                isEquals();
    }
}
