package com.uplift.baggageloadingsystem.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class PorterLog {
    @Id @GeneratedValue(strategy=IDENTITY)
    private Integer id;
    private Timestamp loginTime;
    private Timestamp logoutTime;
    @ManyToOne @JoinColumn(name = "porter_id")
    private Porter porter;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Timestamp getLoginTime() { return loginTime; }

    public void setLoginTime(Timestamp loginTime) { this.loginTime = loginTime; }

    public Timestamp getLogoutTime() { return logoutTime; }

    public void setLogoutTime(Timestamp logoutTime) { this.logoutTime = logoutTime; }

    public Porter getPorter() { return porter; }

    public void setPorter(Porter porter) { this.porter = porter; }

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
        final PorterLog other = (PorterLog) obj;
        return new EqualsBuilder().
                append(this.id, other.id).
                isEquals();
    }
}
