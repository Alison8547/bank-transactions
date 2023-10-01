package com.br.bank.repository;

import com.br.bank.entity.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Integer> {

    Optional<Agency> findByNumberAgency(Integer numberAgency);

    boolean existsByNumberAgency(Integer numberAgency);
}
