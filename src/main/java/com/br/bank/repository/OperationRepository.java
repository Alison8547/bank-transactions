package com.br.bank.repository;

import com.br.bank.dto.response.ExtractResponse;
import com.br.bank.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer> {

    @Query(" select new com.br.bank.dto.response.ExtractResponse(" +
            " c.fullName, " +
            " a.numberAccount, " +
            " o.typeOperation, " +
            " o.valueOperation, " +
            " o.timeOperation" +
            ")" +
            " from Account a" +
            " left join Operation o on o.idAccount = a.id" +
            " left join Client c on a.idClient = c.id " +
            "where (o.dateOperation between :start and :end) and ( a.id = :idAccount)")
    List<ExtractResponse> extract(@Param("start") LocalDate start, @Param("end") LocalDate end, @Param("idAccount") Integer idAccount);

}
