package com.br.bank.service;

import com.br.bank.dto.request.CpfRequest;
import com.br.bank.dto.response.AccountResponse;
import com.br.bank.entity.Account;
import com.br.bank.entity.Client;
import com.br.bank.exception.BusinessException;
import com.br.bank.mapper.AccountMapper;
import com.br.bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper mapper;
    private final ClientService clientService;
    private static final String AGENCY = "0001";
    private static final String BANK_NUMBER = "0260";
    private static final BigDecimal BALANCE_INITIAL = new BigDecimal(0);
    private static final Integer ACTIVE_ACCOUNT = 1;
    private static final Integer DISABLED_ACCOUNT = 0;


    public AccountResponse createAccount(CpfRequest cpfRequest) {
        if (!(clientService.existCpfClient(cpfRequest.getCpf()))) {
            throw new BusinessException("CPF is not registered in our bank account!");
        }

        Client client = clientService.findCpf(cpfRequest.getCpf());

        if (accountRepository.existsByIdClient(client.getId())) {
            throw new BusinessException("There is already an account with this CPF!");
        }

        Account newAccount = Account.builder()
                .agency(AGENCY)
                .bankNumber(BANK_NUMBER)
                .numberAccount(randomNumberAccount())
                .balance(BALANCE_INITIAL.setScale(2, RoundingMode.CEILING))
                .active(ACTIVE_ACCOUNT)
                .idClient(client.getId())
                .client(client)
                .build();

        accountRepository.save(newAccount);
        log.info("Account save success!");

        return mapper.toResponseAccount(newAccount);
    }

    public AccountResponse disabledAccount() {
        Integer clientLogged = clientService.getIdLoggedUser();
        Account accountClient = accountRepository.findByIdClient(clientLogged);
        accountClient.setActive(DISABLED_ACCOUNT);

        accountRepository.save(accountClient);

        return mapper.toResponseAccount(accountClient);
    }

    public AccountResponse activeAccount() {
        Integer clientLogged = clientService.getIdLoggedUser();
        Account accountClient = accountRepository.findByIdClient(clientLogged);
        accountClient.setActive(ACTIVE_ACCOUNT);

        accountRepository.save(accountClient);

        return mapper.toResponseAccount(accountClient);
    }


    public String randomNumberAccount() {
        String generateUUIDNo = String.format("%010d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
        return generateUUIDNo.substring(generateUUIDNo.length() - 9);
    }


}
