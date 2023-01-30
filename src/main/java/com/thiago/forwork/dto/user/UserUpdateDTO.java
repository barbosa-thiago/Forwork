package com.thiago.forwork.dto.user;

import lombok.extern.jackson.Jacksonized;

import java.util.Set;

@Jacksonized
public record UserUpdateDTO(
    String name, Integer department, Set<Long> tasks
){}
