package com.uplift.baggageloadingsystem.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Porter {
    @Id
    private Integer id;
    private String porterNumber;
    private String firstName;
    private String lastName;
    private String status;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="porter_loading_bay",
            joinColumns=@JoinColumn(name="porter_id"),
            inverseJoinColumns=@JoinColumn(name="loading_bay_id"))
    private Collection<LoadingBay> loadingBays;
    @OneToMany(mappedBy = "porter", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<PorterLog> porterLogs;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getPorterNumber() { return porterNumber; }

    public void setPorterNumber(String porterNumber) { this.porterNumber = porterNumber; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public Collection<LoadingBay> getLoadingBays() { return loadingBays; }

    public void setLoadingBays(Collection<LoadingBay> loadingBays) { this.loadingBays = loadingBays; }

    public Collection<PorterLog> getPorterLogs() { return porterLogs; }

    public void setPorterLogs(Collection<PorterLog> porterLogs) { this.porterLogs = porterLogs; }

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
        final Porter other = (Porter) obj;
        return new EqualsBuilder().
                append(this.id, other.id).
                isEquals();
    }
}
