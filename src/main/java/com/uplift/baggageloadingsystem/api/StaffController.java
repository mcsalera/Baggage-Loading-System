package com.uplift.baggageloadingsystem.api;


import com.uplift.baggageloadingsystem.domain.Staff;
import com.uplift.baggageloadingsystem.forms.PassengerForm;
import com.uplift.baggageloadingsystem.forms.StaffForm;
import com.uplift.baggageloadingsystem.service.StaffService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/staff")
public class StaffController {

    private StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }


    @PostMapping("/login")
    public Staff loginStaff(@Validated(StaffForm.Login.class) @RequestBody StaffForm form){
        return staffService.loginStaff(form);
    }

    @PutMapping
    public Staff logoutStaff(@Validated(StaffForm.Logout.class) @RequestBody StaffForm form){
        return staffService.logoutStaff(form);
    }
}
