package com.br.bank.service;

import com.br.bank.dto.request.LoginRequest;
import com.br.bank.entity.Client;
import com.br.bank.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;


    public String authentication(LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        Object principal = authenticate.getPrincipal();
        Client client = (Client) principal;

        return tokenService.getToken(client);
    }
}
