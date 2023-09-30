package com.br.bank.repository;

import com.br.bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    boolean existsByIdClient(Integer idClient);

    Account findByIdClient(Integer idClient);
}
