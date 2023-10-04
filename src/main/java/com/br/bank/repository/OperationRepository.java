package com.br.bank.repository;

import com.br.bank.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer> {

    List<Operation> findAllByTimeOperationBetweenAndId(LocalDateTime start, LocalDateTime end,Integer idOperation);

    Optional<Operation> findByIdAccount(Integer idAccount);
}
