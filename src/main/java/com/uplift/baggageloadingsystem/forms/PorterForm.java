package com.uplift.baggageloadingsystem.forms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uplift.baggageloadingsystem.domain.LoadingBay;
import com.uplift.baggageloadingsystem.domain.Porter;
import com.uplift.baggageloadingsystem.validators.Required;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PorterForm {
    @NotNull(groups = Update.class)
    private Integer id;
    @Required(groups = Create.class)
    private String porterNumber;
    @Required(groups = Create.class)
    private String firstName;
    @Required(groups = Create.class)
    private String lastName;
    private String status;
    private List<Integer> loadingBayIds;
    @JsonIgnore
    private List<LoadingBay> loadingBays;

    public interface Update{

    }
    public interface Create{

    }

    @JsonIgnore
    public Porter toPorter(){
        return Porter
                .builder()
                .id(id)
                .porterNumber(porterNumber)
                .firstName(firstName)
                .lastName(lastName)
                .status(status)
                .build();
    }
}
