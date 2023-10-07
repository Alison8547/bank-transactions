package com.br.bank.controller;

import com.br.bank.dto.request.AccountRequest;
import com.br.bank.dto.response.AccountResponse;
import com.br.bank.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @Operation(summary = "Create your account", description = "Save a person to the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Successfully created"),
                    @ApiResponse(responseCode = "400", description = "An exception was generated"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "500", description = "An internal server exception was generated")
            }
    )
    @PostMapping("/create-account")
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest accountRequest) {
        return new ResponseEntity<>(accountService.createAccount(accountRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Disabled your account", description = "Deactivate your database account")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Edited successfully"),
                    @ApiResponse(responseCode = "400", description = "An exception was generated"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "500", description = "An internal server exception was generated")
            }
    )
    @PutMapping("/disabled-account")
    public ResponseEntity<AccountResponse> disabledAccount() {
        return new ResponseEntity<>(accountService.disabledAccount(), HttpStatus.OK);
    }

    @Operation(summary = "Active your account", description = "Active your database account")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Edited successfully"),
                    @ApiResponse(responseCode = "400", description = "An exception was generated"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "500", description = "An internal server exception was generated")
            }
    )
    @PutMapping("/active-account")
    public ResponseEntity<AccountResponse> activeAccount() {
        return new ResponseEntity<>(accountService.activeAccount(), HttpStatus.OK);
    }
}
