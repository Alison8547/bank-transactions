package com.br.bank.service;

import com.br.bank.dto.request.OperationRequest;
import com.br.bank.dto.response.OperationResponse;
import com.br.bank.entity.Account;
import com.br.bank.entity.Operation;
import com.br.bank.exception.BusinessException;
import com.br.bank.mapper.OperationMapper;
import com.br.bank.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;
    private final OperationMapper mapper;
    private final AccountService accountService;
    private final ClientService clientService;
    private static final Integer DISABLED_ACCOUNT = 0;


    public OperationResponse deposit(OperationRequest operationRequest) {
        Account account = accountService.findByIdClient(clientService.getIdLoggedUser());
        Operation operation = mapper.toEntityOperation(operationRequest);

        if (Objects.equals(account.getActive(), DISABLED_ACCOUNT)) {
            throw new BusinessException("Account blocked, it is not possible to make a deposit!");
        }

        if (!(accountService.existNumberAccount(operation.getNumberAccount()))) {
            throw new BusinessException("Not exist number account!");
        }
        // TODO
        return null;

    }
}
