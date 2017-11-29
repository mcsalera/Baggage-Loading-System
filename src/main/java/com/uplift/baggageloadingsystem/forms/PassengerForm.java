package com.uplift.baggageloadingsystem.forms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uplift.baggageloadingsystem.domain.LoadingBay;
import com.uplift.baggageloadingsystem.domain.Passenger;
import com.uplift.baggageloadingsystem.validators.Required;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class PassengerForm {

    @NotNull(groups = Update.class)
    private Integer id;
    @Required(groups = Create.class)
    private String firstName;
    @Required(groups = Create.class)
    private String lastName;
    @NotNull(groups = Create.class)
    private Integer loadingBayId;
    @NotNull(groups = Create.class)
    private Integer baggageCount;
    @Pattern(regexp = "[\\s]*[0-9]*[1-9]+", message="Invalid number", groups = Create.class)
    @Size(min=11, max = 11, message="Invalid number", groups = Create.class)
    @Required(groups = Create.class)
    private String contactNumber;
    private BigDecimal fee;
    private String code;
    private String passengerQrCodeUrl;
    private List<String> baggagesQrCodeUrl = new ArrayList<>();
    @JsonIgnore
    private LoadingBay loadingBay;
    private String status;

    public interface Update {

    }

    public interface Create {

    }

    @JsonIgnore
    public Passenger toPassenger() {
        return Passenger
                .builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .contactNumber(contactNumber)
                .fee(fee)
                .code(code)
                .qrCodeUrl(passengerQrCodeUrl)
                .loadingBay(loadingBay)
                .status(status)
                .build();
    }
}
