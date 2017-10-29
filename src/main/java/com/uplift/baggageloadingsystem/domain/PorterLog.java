package com.uplift.baggageloadingsystem.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class PorterLog {
    @Id @GeneratedValue(strategy=IDENTITY)
    private Integer id;
    private Timestamp loginTime;
    private Timestamp logoutTime;
    @ManyToOne @JoinColumn(name = "porter_id")
    private Porter porter;

}
