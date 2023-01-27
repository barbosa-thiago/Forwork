package com.thiago.forwork.dto.task;

import com.thiago.forwork.model.Department;

import java.time.LocalDate;
import java.util.List;

public record TaskSaveDTO(
    String title, String description, LocalDate deadline, Department department, List<Long> users
){}
