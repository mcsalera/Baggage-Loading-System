/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uplift.baggageloadingsystem.repository;


import com.uplift.baggageloadingsystem.domain.Passenger;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author Bert
 */
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
