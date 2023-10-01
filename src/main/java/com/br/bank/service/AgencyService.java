package com.br.bank.service;

import com.br.bank.entity.Agency;
import com.br.bank.exception.BusinessException;
import com.br.bank.repository.AgencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgencyService {

    private final AgencyRepository agencyRepository;


    public Agency findAgency(Integer numberAgency) {
        return agencyRepository.findByNumberAgency(numberAgency)
                .orElseThrow(() -> new BusinessException("Agency does not exist!"));
    }

    public boolean existsNumberAgency(Integer numberAgency) {
        return agencyRepository.existsByNumberAgency(numberAgency);
    }
}
