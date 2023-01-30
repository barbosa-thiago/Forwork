package com.thiago.forwork.controllers;

import com.thiago.forwork.dto.department.DepartmentResponseFullDTO;
import com.thiago.forwork.mapper.DepartmentMapper;
import com.thiago.forwork.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("departamentos")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final DepartmentMapper mapper;

    @GetMapping
    public ResponseEntity<List<DepartmentResponseFullDTO>> getAll() {
        var departmentList = departmentService.getAll();
        return ResponseEntity.ok(mapper.departmentsToDepartmentResponseDTOs(departmentList));
    }

}
