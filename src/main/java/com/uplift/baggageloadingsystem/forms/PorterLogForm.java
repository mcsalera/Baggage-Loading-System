package com.uplift.baggageloadingsystem.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor
@Builder
@Data
public class PorterLogForm {
    @NotNull
    private Integer porterId;
    @NotNull
    private boolean login;
    private String porterName;
    private String status;
    private String action;
    private LocalDateTime timestamp;
}
