package com.thiago.forwork.mapper;

import com.thiago.forwork.dto.department.DepartmentResponseDTO;
import com.thiago.forwork.model.Department;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    DepartmentResponseDTO departmentToDepartmentResponseDTO(Department department);

    @IterableMapping(elementTargetType = DepartmentResponseDTO)
    List<DepartmentResponseDTO> departmentsToDepartmentResponseDTOs(List<Department> departments);

}
