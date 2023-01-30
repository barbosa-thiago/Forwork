package com.thiago.forwork.dto.department;

import lombok.extern.jackson.Jacksonized;

@Jacksonized
public record DepartmentDTO(
    Long id, String name
) {
}
