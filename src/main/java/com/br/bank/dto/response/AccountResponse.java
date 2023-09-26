package com.br.bank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    private Integer id;
    private Integer agency;
    private String numberAccount;
    private BigDecimal balance;
    private Integer active;
    private Integer idClient;
}
