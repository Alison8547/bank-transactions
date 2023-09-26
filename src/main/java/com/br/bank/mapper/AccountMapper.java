package com.br.bank.mapper;

import com.br.bank.dto.request.AccountRequest;
import com.br.bank.dto.response.AccountResponse;
import com.br.bank.entity.Account;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountMapper {

    private final ModelMapper mapper;


    public Account toEntityAccount(AccountRequest accountRequest) {
        return mapper.map(accountRequest, Account.class);
    }

    public AccountResponse toResponseAccount(Account account) {
        return mapper.map(account, AccountResponse.class);
    }
}
