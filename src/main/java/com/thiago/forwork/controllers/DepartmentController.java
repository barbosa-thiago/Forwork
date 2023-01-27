package com.thiago.forwork.controllers;

import com.thiago.forwork.dto.department.DepartmentResponseDTO;
import com.thiago.forwork.mapper.DepartmentMapper;
import com.thiago.forwork.model.Department;
import com.thiago.forwork.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("departamento")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final DepartmentMapper mapper;

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> getAll() {
        var departmentList = departmentService.getAll();
        return ResponseEntity.ok(mapper.departmentsToDepartmentResponseDTOs(departmentList));
    }

}
