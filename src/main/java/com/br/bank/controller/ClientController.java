package com.br.bank.controller;

import com.br.bank.dto.request.ClientRequest;
import com.br.bank.dto.response.ClientResponse;
import com.br.bank.service.ClientService;
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
public class ClientController {

    private final ClientService clientService;

    @Operation(summary = "Create client", description = "Save a client to the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Successfully created"),
                    @ApiResponse(responseCode = "400", description = "An exception was generated"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "500", description = "An internal server exception was generated")
            }
    )
    @PostMapping("/create-client")
    public ResponseEntity<ClientResponse> createClient(@Valid @RequestBody ClientRequest clientRequest) {
        return new ResponseEntity<>(clientService.createClient(clientRequest), HttpStatus.CREATED);
    }
}
