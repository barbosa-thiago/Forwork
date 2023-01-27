package com.thiago.forwork.dto.user;

import com.thiago.forwork.dto.department.DepartmentResponseDTO;
import com.thiago.forwork.dto.task.TaskResponseDTO;

import java.util.List;

public record UserResponseDTO(
    Long id, String name, DepartmentResponseDTO department, List<TaskResponseDTO> tasks
){}
