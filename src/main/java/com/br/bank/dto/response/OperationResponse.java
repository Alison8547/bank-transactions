package com.br.bank.dto.response;

import com.br.bank.enums.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationResponse {

    private Integer id;
    private TypeOperation typeOperation;
    private BigDecimal valueOperation;
    private LocalDateTime timeOperation;
    private Integer idAccount;
}
