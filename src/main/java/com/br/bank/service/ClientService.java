package com.br.bank.service;

import com.br.bank.entity.Client;
import com.br.bank.exception.BusinessException;
import com.br.bank.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;


    public Client findEmail(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Email not found!"));
    }
}
