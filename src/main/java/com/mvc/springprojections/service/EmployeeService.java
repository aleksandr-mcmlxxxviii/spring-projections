package com.mvc.springprojections.service;

import com.mvc.springprojections.dto.EmployeeDTO;
import com.mvc.springprojections.projection.EmployeeProjection;
import com.mvc.springprojections.projection.EmployeeProjectionNotSalary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    Page<EmployeeProjection> findEmployeePage(Pageable pageable);
    List<EmployeeProjectionNotSalary> findAllEmployee();
    EmployeeDTO findEmployeeById(Long id);
    EmployeeDTO createEmployee(EmployeeDTO dto);
    EmployeeDTO updateEmployee(Long id, EmployeeDTO dto);
    void deleteEmployee(Long id);

}
