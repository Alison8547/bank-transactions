package com.br.bank.service;

import com.br.bank.dto.request.AccountRequest;
import com.br.bank.dto.response.AccountResponse;
import com.br.bank.entity.Account;
import com.br.bank.entity.Agency;
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
    private final AgencyService agencyService;
    private static final BigDecimal BALANCE_INITIAL = new BigDecimal(0);
    private static final Integer ACTIVE_ACCOUNT = 1;
    private static final Integer DISABLED_ACCOUNT = 0;


    public AccountResponse createAccount(AccountRequest accountRequest) {

        if (!(clientService.existCpfClient(accountRequest.getCpf()))) {
            throw new BusinessException("CPF is not registered in our app!");
        }

        if (!(agencyService.existsNumberAgency(accountRequest.getNumberAgency()))) {
            throw new BusinessException("Agency does not exist!");
        }

        Agency agency = agencyService.findAgency(accountRequest.getNumberAgency());

        Client client = clientService.findCpf(accountRequest.getCpf());

        Account newAccount = Account.builder()
                .numberAccount(randomNumberAccount())
                .balance(BALANCE_INITIAL.setScale(2, RoundingMode.CEILING))
                .active(ACTIVE_ACCOUNT)
                .idClient(client.getId())
                .idAgency(agency.getId())
                .client(client)
                .agency(agency)
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

    public Account findByIdClient(Integer id) {
        return accountRepository.findByIdClient(id);
    }

    public boolean existNumberAccount(String numberAccount) {
        return accountRepository.existsByNumberAccount(numberAccount);
    }

    public String randomNumberAccount() {
        String generateUUIDNo = String.format("%010d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
        return generateUUIDNo.substring(generateUUIDNo.length() - 9);
    }


}
