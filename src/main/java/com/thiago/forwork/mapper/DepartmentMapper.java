package com.thiago.forwork.mapper;

import com.thiago.forwork.dto.department.DepartmentResponseFullDTO;
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

    DepartmentResponseFullDTO departmentToDepartmentResponseDTO(Department department);

    @IterableMapping(elementTargetType = DepartmentResponseFullDTO.class)
    List<DepartmentResponseFullDTO> departmentsToDepartmentResponseDTOs(List<Department> departments);

}
