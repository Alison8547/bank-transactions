package com.br.bank.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    @NotBlank
    @CPF
    @Schema(description = "You cpf", example = "991.205.920-43")
    private String cpf;

    @NotNull
    @Schema(description = "You number agency", example = "266")
    private Integer numberAgency;

}
