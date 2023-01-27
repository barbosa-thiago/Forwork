package com.thiago.forwork.dto.task;

import com.thiago.forwork.dto.department.DepartmentResponseDTO;
import com.thiago.forwork.dto.user.UserResponseDTO;

import java.time.LocalDate;

public record TaskResponseDTO(
    Long id,
    String title,
    String description,
    LocalDate deadline,
    DepartmentResponseDTO department,
    UserResponseDTO user,
    Boolean finished
){}
