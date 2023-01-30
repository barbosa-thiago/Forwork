package com.thiago.forwork.controllers;

import com.thiago.forwork.dto.task.TaskResponseDTO;
import com.thiago.forwork.dto.task.TaskSaveDTO;
import com.thiago.forwork.mapper.TaskMapper;
import com.thiago.forwork.model.Task;
import com.thiago.forwork.service.TaskService;
import jakarta.validation.Valid;
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
    public ResponseEntity<TaskResponseDTO> save(@RequestBody @Valid TaskSaveDTO body) {
        var task = taskService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.taskToTaskResponseDTO(task));
    }

    @PutMapping("/alocar/{id}")
    public ResponseEntity<TaskResponseDTO> alocar(@PathVariable(name = "id") Long taskId, @RequestParam Long userId) {
        var task = taskService.asignUser(taskId, userId);
        return ResponseEntity.ok(mapper.taskToTaskResponseDTO(task));
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<Void> finish(@PathVariable(name = "id") Long id) {
        taskService.finish(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllUnasigned() {
        var tasks = taskService.getUnasignedTasks();
        return ResponseEntity.ok(mapper.tasksToTaskResponseDTOs(tasks));
    }
}
