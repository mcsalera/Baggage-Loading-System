package com.uplift.baggageloadingsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.GenerationType.IDENTITY;

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

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getLoadingBayName() {
        return loadingBayName;
    }

    public void setLoadingBayName(String loadingBayName) {
        this.loadingBayName = loadingBayName;
    }

    public String getVia() { return via; }

    public void setVia(String via) { this.via = via; }

    public String getDestination() { return destination; }

    public void setDestination(String destination) { this.destination = destination; }

    public Collection<CounterStorage> getCounterStorages() { return counterStorages; }

    public void setCounterStorages(Collection<CounterStorage> counterStorages) { this.counterStorages = counterStorages; }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBusCompany() {
        return busCompany;
    }

    public void setBusCompany(String busCompany) {
        this.busCompany = busCompany;
    }


    public Collection<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Collection<Passenger> passengers) {
        this.passengers = passengers;
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
        final LoadingBay other = (LoadingBay) obj;
        return new EqualsBuilder().
                append(this.id, other.id).
                isEquals();
    }
}
