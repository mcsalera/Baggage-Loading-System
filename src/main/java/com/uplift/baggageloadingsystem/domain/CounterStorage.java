package com.uplift.baggageloadingsystem.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class CounterStorage {
    @Id @GeneratedValue(strategy=IDENTITY)
    private Integer id;
    @ManyToOne @JoinColumn(name = "porter_id")
    private Porter porter;
    @ManyToOne @JoinColumn(name = "loading_bay_id")
    private LoadingBay loadingBay;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Porter getPorter() { return porter; }

    public void setPorter(Porter porter) { this.porter = porter; }

    public LoadingBay getLoadingBay() { return loadingBay; }

    public void setLoadingBay(LoadingBay loadingBay) { this.loadingBay = loadingBay; }

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
        final CounterStorage other = (CounterStorage) obj;
        return new EqualsBuilder().
                append(this.id, other.id).
                isEquals();
    }
}
