package com.thiago.forwork.dto.user;

import java.util.Set;

public record UserUpdateDTO(
    String name, Long department, Set<Long> tasks
){}
