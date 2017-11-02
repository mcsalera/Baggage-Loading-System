package com.uplift.baggageloadingsystem.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class PorterLog {
    @Id @GeneratedValue(strategy=IDENTITY)
    private Integer id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime loginTime = LocalDateTime.now();
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime logoutTime = LocalDateTime.now();
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}) @JoinColumn(name = "porter_id")
    private Porter porter;

}
