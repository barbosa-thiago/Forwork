package com.thiago.forwork.dto.user;

import com.thiago.forwork.dto.department.DepartmentResponseFullDTO;
import com.thiago.forwork.dto.task.TimeLoggedDTO;

import java.util.List;

public record UserTimeResponseDTO(
    Long id, String name, DepartmentResponseFullDTO department, List<TimeLoggedDTO> tasks
){}
