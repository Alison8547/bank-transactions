package com.br.bank.controller;

import com.br.bank.dto.request.CpfRequest;
import com.br.bank.dto.response.AccountResponse;
import com.br.bank.service.AccountService;
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

    @PostMapping("/create-account")
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CpfRequest cpfRequest) {
        return new ResponseEntity<>(accountService.createAccount(cpfRequest), HttpStatus.CREATED);
    }

    @PutMapping("/disabled-account")
    public ResponseEntity<AccountResponse> disabledAccount() {
        return new ResponseEntity<>(accountService.disabledAccount(), HttpStatus.OK);
    }
}
