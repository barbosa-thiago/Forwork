package com.thiago.forwork.controllers;

import com.thiago.forwork.dto.department.DepartmentResponseFullDTO;
import com.thiago.forwork.repository.DepartmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
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
class DepartmentControllerTest {

    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    @Sql("/scripts-h2/department.sql")
    void getAll_ReturnsDepartment_WhenSuccessful() {
//        var departmentResponseDTO = FakeFactory.createDepartmentSaveDTO();

        var departmentResponseDTO = restTemplate.exchange(
            "/departamentos", HttpMethod.GET, null,
            new ParameterizedTypeReference<List<DepartmentResponseFullDTO>>() {
            }
        );

        Assertions.assertThat(departmentResponseDTO.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(departmentResponseDTO.getBody().get(0)).hasNoNullFieldsOrProperties();
    }
}