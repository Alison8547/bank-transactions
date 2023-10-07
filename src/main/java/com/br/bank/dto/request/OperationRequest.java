package com.br.bank.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationRequest {


    @NotNull
    @Schema(description = "You number agency", example = "266")
    private Integer numberAgency;

    @NotBlank
    @Schema(description = "You number account", example = "061582783")
    private String numberAccount;

    @NotNull
    @Schema(description = "You value operation", example = "90.00")
    private BigDecimal valueOperation;
}
