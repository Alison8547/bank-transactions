package com.br.bank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {

    private Long totalElements;
    private Integer quantityPages;
    private Integer page;
    private Integer size;
    private List<T> elements;
}
