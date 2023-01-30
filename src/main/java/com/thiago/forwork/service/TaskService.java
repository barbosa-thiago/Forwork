package com.thiago.forwork.service;

import com.thiago.forwork.dto.task.TaskSaveDTO;
import com.thiago.forwork.dto.task.TaskAlocateDTO;
import com.thiago.forwork.mapper.TaskMapper;
import com.thiago.forwork.model.Task;
import com.thiago.forwork.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    public Task save(TaskSaveDTO body) {
        var task = TaskMapper.INSTANCE.taskSaveDTOToTask(body);
        return taskRepository.save(task);
    }

    public List<Task> getUnasignedTasks() {
        return taskRepository.findAll()
            .stream()
            .filter(task -> task.getAsignee() == null)
            .sorted(Comparator.comparing(Task::getDeadline).reversed())
            .limit(3)
            .toList();
    }

    public Task update(Long id, TaskAlocateDTO body) {
        var task = getByID(id);
        TaskMapper.INSTANCE.update(body, task);
        return taskRepository.save(task);
    }

    public Task asignUser(Long taskId, Long userId) {
        var task = getByID(taskId);
        var user = userService.getByID(userId);

        if (!user.getDepartment().equals(task.getDepartment()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario nao pertence ao departamento da tarefa");

        task.setAsignee(user);
        return taskRepository.save(task);
    }

    public Task getByID(Long id) {
        return taskRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "task nao encontrada"));
    }

    public void finish(Long id) {
        var task = getByID(id);
        task.setFinished(true);
        taskRepository.save(task);
    }
}
