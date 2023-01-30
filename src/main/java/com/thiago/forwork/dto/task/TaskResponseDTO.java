package com.thiago.forwork.dto.task;

import com.thiago.forwork.dto.department.DepartmentDTO;
import com.thiago.forwork.dto.user.UserDTO;

import java.time.LocalDate;

public record TaskResponseDTO(
    Long id,
    String title,
    String description,
    LocalDate deadline,
    DepartmentDTO department,
    UserDTO asignee,
    Boolean finished
){}
