package com.br.bank.repository;

import com.br.bank.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByEmail(String email);

    boolean existsByCpf(String cpf);

    Optional<Client> findByCpf(String cpf);
}
