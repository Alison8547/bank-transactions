package com.br.bank.service;

import com.br.bank.dto.request.OperationRequest;
import com.br.bank.dto.request.WithdrawRequest;
import com.br.bank.dto.response.OperationResponse;
import com.br.bank.entity.Account;
import com.br.bank.entity.Operation;
import com.br.bank.enums.TypeOperation;
import com.br.bank.exception.BusinessException;
import com.br.bank.mapper.OperationMapper;
import com.br.bank.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;
    private final OperationMapper mapper;
    private final AccountService accountService;
    private final AgencyService agencyService;
    private final ClientService clientService;
    private static final Integer DISABLED_ACCOUNT = 0;


    public OperationResponse deposit(OperationRequest operationRequest) {

        Account account = accountService.findByIdClient(clientService.getIdLoggedUser());
        Operation operation = mapper.toEntityOperation(operationRequest);

        validationsOperationsDeposit(operationRequest, account);
        operation.setTypeOperation(TypeOperation.DEPOSIT);
        operation.setTimeOperation(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        operation.setValueOperation(operationRequest.getValueOperation().setScale(2, RoundingMode.CEILING));
        operation.setIdAccount(account.getId());
        operation.setAccount(account);


        operationRepository.save(operation);
        log.info("Save operation success!");

        account.setBalance(operation.getValueOperation().add(account.getBalance()));
        accountService.save(account);
        log.info("Update balance account success!");

        return mapper.toResponseOperation(operation);
    }


    public OperationResponse withdraw(WithdrawRequest withdrawRequest) {
        Account account = accountService.findByIdClient(clientService.getIdLoggedUser());
        Operation operation = mapper.toEntityOperation(withdrawRequest);

        if (withdrawRequest.getValueOperation().doubleValue() > account.getBalance().doubleValue()) {
            throw new BusinessException("Your withdrawal is bigger than the money you have!");
        }

        if (Objects.equals(account.getActive(), DISABLED_ACCOUNT)) {
            throw new BusinessException("Account blocked, it is not possible to make a deposit!");
        }

        operation.setTypeOperation(TypeOperation.WITHDRAW);
        operation.setTimeOperation(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        operation.setValueOperation(withdrawRequest.getValueOperation().setScale(2, RoundingMode.CEILING));
        operation.setIdAccount(account.getId());
        operation.setAccount(account);


        operationRepository.save(operation);
        log.info("Save operation success!");

        account.setBalance(account.getBalance().subtract(operation.getValueOperation()));
        accountService.save(account);
        log.info("Update balance account success!");

        return mapper.toResponseOperation(operation);
    }

    public OperationResponse transfer(OperationRequest operationRequest) {
        Account account = accountService.findByIdClient(clientService.getIdLoggedUser());
        Operation operation = mapper.toEntityOperation(operationRequest);

        validationsOperationTransfer(operationRequest, account);

        operation.setTypeOperation(TypeOperation.TRANSFER);
        operation.setTimeOperation(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        operation.setValueOperation(operationRequest.getValueOperation().setScale(2, RoundingMode.CEILING));
        operation.setIdAccount(account.getId());
        operation.setAccount(account);


        operationRepository.save(operation);
        log.info("Save operation success!");

        account.setBalance(account.getBalance().subtract(operation.getValueOperation()));
        accountService.save(account);
        log.info("Update balance account success!");


        Account accountTransfer = accountService.findByAccountNumber(operationRequest.getNumberAccount());

        accountTransfer.setBalance(operationRequest.getValueOperation().add(accountTransfer.getBalance()));
        accountService.save(accountTransfer);
        log.info("Transfer success for client: {}", accountTransfer.getClient().getFullName());

        return mapper.toResponseOperation(operation);
    }


    private void validationsOperationsDeposit(OperationRequest operationRequest, Account account) {
        if (Objects.equals(account.getActive(), DISABLED_ACCOUNT)) {
            throw new BusinessException("Account blocked, it is not possible to make a deposit!");
        }

        if (!(accountService.existNumberAccount(operationRequest.getNumberAccount()))) {
            throw new BusinessException("Not exist number account!");
        }

        if (!(agencyService.existsNumberAgency(operationRequest.getNumberAgency()))) {
            throw new BusinessException("Not exist number agency!");
        }
    }

    private void validationsOperationTransfer(OperationRequest operationRequest, Account account) {
        if (operationRequest.getValueOperation().doubleValue() > account.getBalance().doubleValue()) {
            throw new BusinessException("Your transfer is bigger than the money you have!");
        }

        if (!(accountService.existNumberAccount(operationRequest.getNumberAccount()))) {
            throw new BusinessException("Not exist number account!");
        }

        if (!(agencyService.existsNumberAgency(operationRequest.getNumberAgency()))) {
            throw new BusinessException("Not exist number agency!");
        }

        if (Objects.equals(account.getActive(), DISABLED_ACCOUNT)) {
            throw new BusinessException("Account blocked, it is not possible to make a deposit!");
        }
    }
}
