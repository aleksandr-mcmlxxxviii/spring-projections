package com.mvc.springprojections.mapper;

import com.mvc.springprojections.dto.DepartmentDTO;
import com.mvc.springprojections.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface DepartmentMapper {

    DepartmentDTO toDto (Department department);

    @Mapping(target = "id", ignore = true)
    Department toEntity (DepartmentDTO departmentDTO);

    void updateEntity(DepartmentDTO dto, @MappingTarget Department department);
}
