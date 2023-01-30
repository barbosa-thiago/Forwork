package com.thiago.forwork.dto.department;

import com.thiago.forwork.dto.task.TaskResponseDTO;
import com.thiago.forwork.dto.user.UserResponseFullDTO;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
public record DepartmentResponseFullDTO(
    Long id, String name, List<UserResponseFullDTO> users, List<TaskResponseDTO> tasks
) {
}
