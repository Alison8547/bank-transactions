package com.br.bank.service;

import com.br.bank.entity.Cargo;
import com.br.bank.exception.BusinessException;
import com.br.bank.repository.CargoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CargoService {

    private final CargoRepository cargoRepository;


    public Cargo findCargo(Integer id) {
        return cargoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cargo not found!"));
    }
}
