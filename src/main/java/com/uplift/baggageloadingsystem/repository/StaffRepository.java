/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uplift.baggageloadingsystem.repository;

import com.uplift.baggageloadingsystem.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

/**
 *
 * @author Bert
 */
public interface StaffRepository extends JpaRepository<Staff, Integer> {

}
