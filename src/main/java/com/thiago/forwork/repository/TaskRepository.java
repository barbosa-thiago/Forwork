package com.thiago.forwork.repository;

import com.thiago.forwork.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
