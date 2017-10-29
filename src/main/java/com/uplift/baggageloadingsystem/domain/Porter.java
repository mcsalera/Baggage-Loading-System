package com.uplift.baggageloadingsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
public class Porter {
    @Id
    private Integer id;
    private String porterNumber;
    private String firstName;
    private String lastName;
    private String status;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name="porter_loading_bay",
            joinColumns=@JoinColumn(name="porter_id"),
            inverseJoinColumns=@JoinColumn(name="loading_bay_id"))
    private Collection<LoadingBay> loadingBays;
    @JsonIgnore
    @OneToMany(mappedBy = "porter", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<PorterLog> porterLogs;

}
