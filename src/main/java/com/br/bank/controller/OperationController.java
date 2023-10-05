package com.br.bank.controller;

import com.br.bank.dto.request.OperationRequest;
import com.br.bank.dto.request.WithdrawRequest;
import com.br.bank.dto.response.ExtractResponse;
import com.br.bank.dto.response.OperationResponse;
import com.br.bank.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequestMapping
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;

    @PostMapping("/deposit")
    public ResponseEntity<OperationResponse> deposit(@Valid @RequestBody OperationRequest operationRequest) {
        return new ResponseEntity<>(operationService.deposit(operationRequest), HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<OperationResponse> deposit(@Valid @RequestBody WithdrawRequest withdrawRequest) {
        return new ResponseEntity<>(operationService.withdraw(withdrawRequest), HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<OperationResponse> transfer(@Valid @RequestBody OperationRequest operationRequest) {
        return new ResponseEntity<>(operationService.transfer(operationRequest), HttpStatus.OK);
    }

    @GetMapping("/extract")
    public ResponseEntity<List<ExtractResponse>> extract(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate start, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate end) {
        return new ResponseEntity<>(operationService.consultExtract(start, end), HttpStatus.OK);
    }
}
