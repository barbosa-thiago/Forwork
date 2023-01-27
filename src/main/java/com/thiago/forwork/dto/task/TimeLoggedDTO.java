package com.thiago.forwork.dto.task;

public record TimeLoggedDTO(
    Long id,
    Long timeSpentInSeconds,
    Boolean finished
){}
