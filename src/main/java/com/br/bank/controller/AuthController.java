package com.br.bank.controller;

import com.br.bank.dto.request.LoginRequest;
import com.br.bank.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Authenticate your account", description = "Authenticate your database account")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Auth successfully"),
                    @ApiResponse(responseCode = "400", description = "An exception was generated"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "500", description = "An internal server exception was generated")
            }
    )
    @PostMapping("/auth")
    public ResponseEntity<String> auth(@Valid @RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(authService.authentication(loginRequest), HttpStatus.OK);
    }
}
