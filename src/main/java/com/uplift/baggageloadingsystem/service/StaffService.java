package com.uplift.baggageloadingsystem.service;

import com.uplift.baggageloadingsystem.domain.Staff;
import com.uplift.baggageloadingsystem.forms.StaffForm;
import com.uplift.baggageloadingsystem.repository.StaffRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

    private StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository){
        this.staffRepository = staffRepository;
    }

    public Staff loginStaff(StaffForm form) {
        Staff staff = staffRepository.findByUsername(form.getUsername());
        if(staff.getPassword().equals(form.getPassword()))
            staff.setAuthenticated(true);
        return staffRepository.save(staff);
    }

    public Staff logoutStaff(StaffForm form){
        Staff staff = staffRepository.findByUsername(form.getUsername());
        staff.setAuthenticated(false);
        return staffRepository.save(staff);
    }
}
