package com.thiago.forwork.dto.user;

import com.thiago.forwork.dto.department.DepartmentResponseDTO;
import com.thiago.forwork.dto.task.TimeLoggedDTO;

import java.util.List;

public record UserTimeResponseDTO(
    Long id, String name, DepartmentResponseDTO department, List<TimeLoggedDTO> tasks
){}
