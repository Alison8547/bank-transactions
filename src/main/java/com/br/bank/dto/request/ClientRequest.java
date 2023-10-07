package com.br.bank.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {


    @NotBlank
    @Email
    @Schema(description = "You email", example = "alison@hotmail.com")
    private String email;

    @NotBlank
    @Schema(description = "You password", example = "123")
    private String password;

    @NotBlank
    @CPF
    @Schema(description = "You cpf", example = "991.205.920-43")
    private String cpf;

    @NotBlank
    @Schema(description = "You full name", example = "Alison silva santos")
    private String fullName;

}
