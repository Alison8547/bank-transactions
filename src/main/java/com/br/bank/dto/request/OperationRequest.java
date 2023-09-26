package com.br.bank.dto.request;

import com.br.bank.enums.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationRequest {

    private TypeOperation typeOperation;
    private Integer agency;
    private String numberAccount;
    private BigDecimal valueOperation;
}
