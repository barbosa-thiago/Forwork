package com.thiago.forwork.controllers;

import com.thiago.forwork.common.FakeFactory;
import com.thiago.forwork.dto.user.UserResponseFullDTO;
import com.thiago.forwork.repository.UserRepository;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    @Test
    @Order(0)
    @Sql("/scripts-h2/department.sql")
    void save_PersistsUser_WhenSuccessful() {
        var userSaveDTO = FakeFactory.createUserSaveDTO();

        var userResponseDTO = restTemplate.exchange(
            "/pessoas", HttpMethod.POST,
            new HttpEntity<>(userSaveDTO),
            UserResponseFullDTO.class
        );

        Assertions.assertThat(userResponseDTO.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(userResponseDTO.getBody()).hasNoNullFieldsOrProperties();
        Assertions.assertThat(userResponseDTO.getBody().name()).isEqualTo(userSaveDTO.name());
    }

    @Test
    @Sql({"/scripts-h2/department.sql", "/scripts-h2/user.sql"})
    void getAll_ReturnsUser_WhenSuccessful() {
        var userResponseDTO = restTemplate.exchange(
            "/pessoas", HttpMethod.GET, null,
            new ParameterizedTypeReference<PageableResponse<UserResponseFullDTO>>() {
            }
        );

        Assertions.assertThat(userResponseDTO.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(userResponseDTO.getBody().getContent().get(0)).hasNoNullFieldsOrProperties();
    }

    @Test
    @Sql({"/scripts-h2/department.sql", "/scripts-h2/user.sql"})
    void getByName_ReturnsUser_WhenNameMatches() {
        var userResponseDTO = restTemplate.exchange(
            "/pessoas/gastos?name=hiag", HttpMethod.GET, null,
            new ParameterizedTypeReference<PageableResponse<UserResponseFullDTO>>() {
            }
        );

        Assertions.assertThat(userResponseDTO.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(userResponseDTO.getBody().getContent().get(0)).hasNoNullFieldsOrProperties();
    }

    @Test
    @Sql({"/scripts-h2/department.sql", "/scripts-h2/user.sql"})
    void update_AlterUser_WhenSuccessful() {
        var userUpdateDTO = FakeFactory.createUserUpdateDTO();

        var userResponseDTO = restTemplate.exchange(
            "/pessoas/1", HttpMethod.PUT,
            new HttpEntity<>(userUpdateDTO),
            UserResponseFullDTO.class
        );

        Assertions.assertThat(userResponseDTO.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(userResponseDTO.getBody()).hasNoNullFieldsOrProperties();
        Assertions.assertThat(userResponseDTO.getBody().name()).isEqualTo(userUpdateDTO.name());
    }

    @Test
    @Sql({"/scripts-h2/department.sql", "/scripts-h2/user.sql"})
    void delete_RemovesUser_WhenSuccessful() {

        var userResponseDTO = restTemplate.exchange(
            "/pessoas/1", HttpMethod.DELETE,
            null,
            UserResponseFullDTO.class
        );

        Assertions.assertThat(userResponseDTO.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        Assertions.assertThat(userRepository.findById(1L)).isNotPresent();
    }
}