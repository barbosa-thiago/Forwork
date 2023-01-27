package com.thiago.forwork.filter;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Slf4j
@Getter
@Setter
public abstract class PageQueryFilter {
    @Min(value = 0)
    private int page = 0;

    @Min(value = 10)
    @Max(value = 100)
    private int size = 20;

    private String sortKey = "id";

    private Sort.Direction direction = Sort.Direction.ASC;

    public Sort pageSort() {
        return Sort.by(direction, sortKey);
    }

    public PageRequest pageRequest() {
        return PageRequest.of(page, size, direction, sortKey);
    }
}