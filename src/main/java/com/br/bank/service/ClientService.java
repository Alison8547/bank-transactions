package com.br.bank.service;

import com.br.bank.dto.request.ClientRequest;
import com.br.bank.dto.response.ClientResponse;
import com.br.bank.entity.Client;
import com.br.bank.exception.BusinessException;
import com.br.bank.mapper.ClientMapper;
import com.br.bank.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper mapper;
    private final CargoService cargoService;
    private final PasswordEncoder passwordEncoder;
    private static final Integer ACTIVE_USER = 1;
    private static final Integer DISABLED_USER = 0;
    private static final Integer ROLE_CLIENT = 2;
    private static final Integer ROLE_ADMIN = 1;


    public ClientResponse createClient(ClientRequest clientRequest) {
        Client client = mapper.toEntityClient(clientRequest);
        client.setActive(ACTIVE_USER);
        client.setIdCargo(ROLE_CLIENT);
        client.setCargo(cargoService.findCargo(ROLE_CLIENT));
        client.setPassword(passwordEncoder.encode(clientRequest.getPassword()));

        clientRepository.save(client);
        log.info("Client save success!");

        return mapper.toResponseClient(client);
    }

    public Integer getIdLoggedUser() {
        return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }

    public Client findEmail(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Email not found!"));
    }

    public Client findCpf(String cpf) {
        return clientRepository.findByCpf(cpf)
                .orElseThrow(() -> new BusinessException("Client not found!"));
    }

    public Client findClient(Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Client not found!"));
    }

    public boolean existCpfClient(String cpf) {
        return clientRepository.existsByCpf(cpf);
    }
}
