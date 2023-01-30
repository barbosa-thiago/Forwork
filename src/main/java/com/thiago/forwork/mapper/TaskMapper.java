package com.thiago.forwork.mapper;

import com.thiago.forwork.dto.task.TaskAlocateDTO;
import com.thiago.forwork.dto.task.TaskResponseDTO;
import com.thiago.forwork.dto.task.TaskSaveDTO;
import com.thiago.forwork.model.Task;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "department", target = "department.id")
    @Mapping(source = "asignee", target = "asignee.id")
    Task taskSaveDTOToTask(TaskSaveDTO taskSaveDTO);

    TaskResponseDTO taskToTaskResponseDTO(Task task);

    @IterableMapping(elementTargetType = TaskResponseDTO.class)
    List<TaskResponseDTO> tasksToTaskResponseDTOs(List<Task> task);

    void update(TaskAlocateDTO taskAlocateDTO, @MappingTarget Task task);
}
