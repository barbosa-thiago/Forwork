package com.thiago.forwork.controllers;

import com.thiago.forwork.dto.user.UserResponseFullDTO;
import com.thiago.forwork.dto.user.UserSaveDTO;
import com.thiago.forwork.dto.user.UserTimeResponseDTO;
import com.thiago.forwork.dto.user.UserUpdateDTO;
import com.thiago.forwork.filter.UserFilter;
import com.thiago.forwork.mapper.UserMapper;
import com.thiago.forwork.service.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<UserResponseFullDTO> save(@RequestBody @Valid UserSaveDTO body) {
        var user = userService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.userToUserResponseDTO(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseFullDTO> update(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO body) {
        var user = userService.update(id, body);
        return ResponseEntity.ok(mapper.userToUserResponseDTO(user));
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseFullDTO>> getAll(@ParameterObject Pageable pageable) {
        var users = userService.getAll(pageable);
        return ResponseEntity.ok(mapper.usersToUserResponseDTOs(users));
    }

    @GetMapping("/gastos")
    public ResponseEntity<Page<UserTimeResponseDTO>> getByNameAndPeriod(@Valid UserFilter filter) {
        var users = userService.getByNameAndPeriod(filter);
        return ResponseEntity.ok(mapper.usersToUserTimeResponseDTOs(users));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
