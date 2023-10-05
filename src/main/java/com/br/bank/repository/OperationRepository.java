package com.br.bank.repository;

import com.br.bank.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer> {

    //  List<Operation> findAllByDateOperationBetweenAndId(LocalDate start, LocalDate end, Integer idOperation);

    Optional<Operation> findByIdAccount(Integer idAccount);
}
