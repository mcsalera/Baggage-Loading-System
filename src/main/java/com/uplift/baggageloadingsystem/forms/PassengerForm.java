package com.uplift.baggageloadingsystem.forms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uplift.baggageloadingsystem.domain.LoadingBay;
import com.uplift.baggageloadingsystem.domain.Passenger;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PassengerForm {

    @NotNull(groups = Update.class)
    private Integer id;
    @NotBlank(groups = Create.class) @NotEmpty(groups = Create.class)
    private String firstName;
    @NotBlank(groups = Create.class) @NotEmpty(groups = Create.class)
    private String lastName;
    @NotNull(groups = Create.class)
    private Double baggageWeight;
    @NotNull(groups = Create.class)
    private Integer loadingBayId;
    @NotNull(groups = Create.class)
    private Integer baggageCount;
    @NotBlank(groups = Create.class) @NotEmpty(groups = Create.class)
    private String contactNumber;
    private BigDecimal fee;
    private String code;
    private String passengerQrCodeUrl;
    @JsonIgnore
    private LoadingBay loadingBay;


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
                .baggageWeight(baggageWeight)
                .contactNumber(contactNumber)
                .fee(fee)
                .code(code)
                .qrCodeUrl(passengerQrCodeUrl)
                .loadingBay(loadingBay)
                .build();
    }


}
