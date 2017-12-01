package com.uplift.baggageloadingsystem.forms;

import com.uplift.baggageloadingsystem.validators.Required;
import lombok.Data;


@Data
public class StaffForm {

    @Required(groups = {Login.class, Logout.class})
    private String username;
    @Required(groups = {Login.class})
    private String password;

    public interface Login{

    }

    public interface Logout{

    }
}
