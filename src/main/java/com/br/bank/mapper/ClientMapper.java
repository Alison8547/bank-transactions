package com.br.bank.mapper;

import com.br.bank.dto.request.ClientRequest;
import com.br.bank.dto.response.ClientResponse;
import com.br.bank.entity.Client;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientMapper {

    private final ModelMapper mapper;


    public Client toEntityClient(ClientRequest clientRequest) {
        return mapper.map(clientRequest, Client.class);
    }

    public ClientResponse toResponseClient(Client client) {
        return mapper.map(client, ClientResponse.class);
    }
}
