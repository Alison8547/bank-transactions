package com.br.bank.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

    @NotBlank
    @Schema(description = "You email", example = "alison@hotmail.com")
    private String email;

    @NotBlank
    @Schema(description = "You password", example = "123")
    private String password;
}
