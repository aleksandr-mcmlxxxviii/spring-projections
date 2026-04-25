package com.mvc.springprojections.mapper;

import com.mvc.springprojections.dto.EmployeeDTO;
import com.mvc.springprojections.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EmployeeMapper {

    @Mapping(target = "departmentId", source = "department.id")
    EmployeeDTO toDto(Employee employee);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    Employee toEntity(EmployeeDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    void updateEntity(EmployeeDTO dto, @MappingTarget Employee employee);
}
