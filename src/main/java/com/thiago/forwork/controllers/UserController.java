package com.thiago.forwork.controllers;

import com.thiago.forwork.dto.user.UserResponseDTO;
import com.thiago.forwork.dto.user.UserSaveDTO;
import com.thiago.forwork.dto.user.UserTimeResponseDTO;
import com.thiago.forwork.dto.user.UserUpdateDTO;
import com.thiago.forwork.filter.UserFilter;
import com.thiago.forwork.mapper.UserMapper;
import com.thiago.forwork.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("pessoas")
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(UserSaveDTO body) {
        var user = userService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.userToUserResponseDTO(user));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, UserUpdateDTO body) {
        userService.update(id, body);
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAll(@ParameterObject Pageable pageable) {
        var users = userService.getAll(pageable);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.usersToUserResponseDTOs(users));
    }

    @GetMapping("/gastos")
    public ResponseEntity<Page<UserTimeResponseDTO>> getByNameAndPeriod(UserFilter filter) {
        var users = userService.getByNameAndPeriod(filter);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.usersToUserTimeResponseDTOs(users));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
