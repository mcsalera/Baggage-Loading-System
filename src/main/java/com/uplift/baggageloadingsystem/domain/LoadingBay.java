package com.uplift.baggageloadingsystem.domain;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class LoadingBay {
    @Id @GeneratedValue(strategy=IDENTITY)
    private Integer id;
    private String via;
    private String destination;
    @OneToMany(mappedBy = "bus", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<Bus> buses;
    @OneToMany(mappedBy = "counterStorage", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<CounterStorage> counterStorages;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getVia() { return via; }

    public void setVia(String via) { this.via = via; }

    public String getDestination() { return destination; }

    public void setDestination(String destination) { this.destination = destination; }

    public Collection<Bus> getBuses() { return buses; }

    public void setBuses(Collection<Bus> buses) { this.buses = buses; }

    public Collection<CounterStorage> getCounterStorages() { return counterStorages; }

    public void setCounterStorages(Collection<CounterStorage> counterStorages) { this.counterStorages = counterStorages; }

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
