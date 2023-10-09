package com.br.bank.controller;

import com.br.bank.dto.request.OperationRequest;
import com.br.bank.dto.request.WithdrawRequest;
import com.br.bank.dto.response.ExtractResponse;
import com.br.bank.dto.response.OperationResponse;
import com.br.bank.dto.response.PageResponse;
import com.br.bank.service.OperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Validated
@RestController
@RequestMapping
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;

    @Operation(summary = "Deposit your account", description = "Deposit your database account")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successful transaction"),
                    @ApiResponse(responseCode = "400", description = "An exception was generated"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "500", description = "An internal server exception was generated")
            }
    )
    @PostMapping("/deposit")
    public ResponseEntity<OperationResponse> deposit(@Valid @RequestBody OperationRequest operationRequest) {
        return new ResponseEntity<>(operationService.deposit(operationRequest), HttpStatus.OK);
    }


    @Operation(summary = "Withdraw your account", description = "Withdraw your database account")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successful transaction"),
                    @ApiResponse(responseCode = "400", description = "An exception was generated"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "500", description = "An internal server exception was generated")
            }
    )
    @PostMapping("/withdraw")
    public ResponseEntity<OperationResponse> deposit(@Valid @RequestBody WithdrawRequest withdrawRequest) {
        return new ResponseEntity<>(operationService.withdraw(withdrawRequest), HttpStatus.OK);
    }

    @Operation(summary = "Transfer your account", description = "Transfer your database account")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successful transaction"),
                    @ApiResponse(responseCode = "400", description = "An exception was generated"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "500", description = "An internal server exception was generated")
            }
    )
    @PostMapping("/transfer")
    public ResponseEntity<OperationResponse> transfer(@Valid @RequestBody OperationRequest operationRequest) {
        return new ResponseEntity<>(operationService.transfer(operationRequest), HttpStatus.OK);
    }

    @Operation(summary = "Extract your account", description = "A list of database extract")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successful transaction"),
                    @ApiResponse(responseCode = "400", description = "An exception was generated"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "500", description = "An internal server exception was generated")
            }
    )
    @GetMapping("/extract")
    public ResponseEntity<PageResponse<ExtractResponse>> extract(Integer page, Integer size, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate start, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate end) {
        return new ResponseEntity<>(operationService.consultExtract(page, size, start, end), HttpStatus.OK);
    }
}
