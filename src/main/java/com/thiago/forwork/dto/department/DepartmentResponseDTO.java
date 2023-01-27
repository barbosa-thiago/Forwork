package com.thiago.forwork.dto.department;

import com.thiago.forwork.dto.task.TaskResponseDTO;
import com.thiago.forwork.dto.user.UserResponseDTO;

import java.util.List;

public record DepartmentResponseDTO(
    String name, List<UserResponseDTO> users, List<TaskResponseDTO> tasks
) {
}
