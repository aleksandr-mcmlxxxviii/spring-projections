package com.mvc.springprojections.service;

import com.mvc.springprojections.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {

    List<DepartmentDTO> findAllDepartments ();
    DepartmentDTO findDepartmentById (Long id);
    DepartmentDTO createDepartment (DepartmentDTO dto);
    DepartmentDTO updateDepartment (Long id, DepartmentDTO dto);
    void deleteDepartment (Long id);

}
