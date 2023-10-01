package com.br.bank.dto.request;

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
    private Integer agency;

    @NotBlank
    private String numberAccount;

    @NotNull
    private BigDecimal valueOperation;
}
