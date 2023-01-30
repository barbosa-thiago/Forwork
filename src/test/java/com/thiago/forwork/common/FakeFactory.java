package com.thiago.forwork.common;

import com.thiago.forwork.dto.department.DepartmentResponseFullDTO;
import com.thiago.forwork.dto.task.TaskSaveDTO;
import com.thiago.forwork.dto.user.UserSaveDTO;
import com.thiago.forwork.dto.user.UserUpdateDTO;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class FakeFactory {

    public static DepartmentResponseFullDTO createDepartmentSaveDTO() {
        return new DepartmentResponseFullDTO(1L,
            "Tecnico",
            Collections.emptyList(),
            Collections.emptyList());
    }

    public static UserUpdateDTO createUserUpdateDTO() {
        return new UserUpdateDTO(
            "Ricardo",
            1,
            Collections.emptySet());
    }

    public static UserSaveDTO createUserSaveDTO() {
        return new UserSaveDTO(
            "Thiago",
            1);
    }

    public static TaskSaveDTO createTaskSaveDTO() {
        return new TaskSaveDTO(
            "Gerenciamento de usuarios",
            "implementar crud de usuarios",
            LocalDate.now().plusDays(5),
            1L,
            1L);
    }
}
