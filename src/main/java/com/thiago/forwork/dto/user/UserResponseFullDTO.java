package com.thiago.forwork.dto.user;

import com.thiago.forwork.dto.department.DepartmentDTO;
import com.thiago.forwork.dto.task.TaskResponseDTO;

import java.util.List;

public record UserResponseFullDTO(
    Long id, String name, DepartmentDTO department, List<TaskResponseDTO> tasks
){}
