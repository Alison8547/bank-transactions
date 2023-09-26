package com.br.bank.mapper;

import com.br.bank.dto.request.OperationRequest;
import com.br.bank.dto.response.OperationResponse;
import com.br.bank.entity.Operation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OperationMapper {

    private final ModelMapper mapper;

    public Operation toEntityOperation(OperationRequest operationRequest) {
        return mapper.map(operationRequest, Operation.class);
    }

    public OperationResponse toResponseOperation(Operation operation) {
        return mapper.map(operation, OperationResponse.class);
    }
}
