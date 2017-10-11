package com.uplift.baggageloadingsystem.domain;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Bus {
    @Id @GeneratedValue(strategy=IDENTITY)
    private Integer id;
    private String plateNumber;
    private String busCompany;
    private Integer loadingBayId;
    @ManyToOne @JoinColumn(name = "bus_id")
    private LoadingBay loadingBay;
    @OneToMany(mappedBy = "passenger", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Collection<Passenger> passengers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBusCompany() { return busCompany; }

    public void setBusCompany(String busCompany) { this.busCompany = busCompany; }

    public Integer getLoadingBayId() { return loadingBayId; }

    public void setLoadingBayId(Integer loadingBayId) { this.loadingBayId = loadingBayId; }

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
        final Bus other = (Baggage) obj;
        return new EqualsBuilder().
                append(this.id, other.id).
                isEquals();
    }
}
