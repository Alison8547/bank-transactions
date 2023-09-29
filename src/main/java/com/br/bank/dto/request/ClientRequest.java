package com.br.bank.dto.request;

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
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    private String fullName;

}
