package com.thiago.forwork.controllers;

import com.thiago.forwork.common.FakeFactory;
import com.thiago.forwork.dto.task.TaskResponseDTO;
import com.thiago.forwork.model.Task;
import com.thiago.forwork.repository.TaskRepository;
import com.thiago.forwork.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    TaskRepository taskRepository;

    @Test
    @Order(0)
    @Sql({"/scripts-h2/department.sql", "/scripts-h2/user.sql"})
    void save_PersistsUser_WhenSuccessful() {
        var taskSaveDTO = FakeFactory.createTaskSaveDTO();

        var exchange = restTemplate.exchange(
            "/tarefas", HttpMethod.POST,
            new HttpEntity<>(taskSaveDTO),
            TaskResponseDTO.class
        );

        org.assertj.core.api.Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        org.assertj.core.api.Assertions.assertThat(exchange.getBody()).hasNoNullFieldsOrProperties();
        Assertions.assertThat(exchange.getBody().title()).isEqualTo(taskSaveDTO.title());
    }

    @Test
    @Sql({"/scripts-h2/department.sql", "/scripts-h2/user.sql", "/scripts-h2/task.sql"})
    void alocate_AlocatesUserToTask_WhenSuccessful() {

        var exchange = restTemplate.exchange(
            "/tarefas/alocar/1?userId=2", HttpMethod.PUT,
            null,
            TaskResponseDTO.class
        );

        org.assertj.core.api.Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
        org.assertj.core.api.Assertions.assertThat(exchange.getBody()).hasNoNullFieldsOrProperties();
    }

    @Test
    @Sql({"/scripts-h2/department.sql", "/scripts-h2/user.sql", "/scripts-h2/task.sql"})
    void finish_setTaskFinished_WhenSuccessful() {

        var exchange = restTemplate.exchange(
            "/tarefas/finalizar/1", HttpMethod.PUT,
            null, Void.class
        );

        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        Assertions.assertThat(taskRepository.findById(1L).get().getFinished()).isTrue();
    }

    @Test
    @Sql({"/scripts-h2/department.sql", "/scripts-h2/user.sql", "/scripts-h2/task.sql"})
    void getAll_ReturnTasks_WhenSuccessful() {

        var exchange = restTemplate.exchange(
            "/tarefas", HttpMethod.GET,
            null, new ParameterizedTypeReference<List<TaskResponseDTO>>() {
            }
        );

        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(exchange.getBody().get(0)).isNotNull();
    }
}