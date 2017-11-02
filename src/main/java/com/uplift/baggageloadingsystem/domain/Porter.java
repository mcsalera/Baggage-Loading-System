package com.uplift.baggageloadingsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor @AllArgsConstructor
@Data
@Entity
@Builder
public class Porter {
    @Id @GeneratedValue(strategy=IDENTITY)
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
