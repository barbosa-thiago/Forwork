package com.thiago.forwork.dto.task;

import java.time.LocalDate;
import java.util.List;

public record TaskSaveDTO(
    String title, String description, LocalDate deadline, Long department, Long asignee
){}
