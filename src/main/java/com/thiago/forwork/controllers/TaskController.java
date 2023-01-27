package com.thiago.forwork.controllers;

import com.thiago.forwork.dto.task.TaskResponseDTO;
import com.thiago.forwork.dto.task.TaskSaveDTO;
import com.thiago.forwork.mapper.TaskMapper;
import com.thiago.forwork.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("tarefas")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper mapper;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> save(TaskSaveDTO body) {
        var task = taskService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.taskToTaskResponseDTO(task));
    }

    @PutMapping("/alocar/{id}")
    public void alocar(@PathVariable(name = "id") Long taskId, @RequestParam Long userId) {
        taskService.asignUser(taskId, userId);
    }

    @PutMapping("/finalizar/{id}")
    public void alocar(@PathVariable(name = "id") Long id) {
        taskService.finish(id);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAll() {
        var tasks = taskService.getUnasignedTasks();
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.tasksToTaskResponseDTOs(tasks));
    }
}
