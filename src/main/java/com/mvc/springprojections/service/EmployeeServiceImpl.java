package com.mvc.springprojections.service;

import com.mvc.springprojections.dto.EmployeeDTO;
import com.mvc.springprojections.entity.Department;
import com.mvc.springprojections.entity.Employee;
import com.mvc.springprojections.exception.ResourceNotFoundException;
import com.mvc.springprojections.mapper.EmployeeMapper;
import com.mvc.springprojections.projection.EmployeeProjection;
import com.mvc.springprojections.projection.EmployeeProjectionNotSalary;
import com.mvc.springprojections.repository.DepartmentRepository;
import com.mvc.springprojections.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;

    private static final String MESSAGE_EMPLOYEE_NOT_FOUND = "Сотрудник с id %d не найден";

    public Page<EmployeeProjection> findEmployeePage (Pageable pageable){
        return employeeRepository.findAllBy(pageable);
    }

    public List<EmployeeProjectionNotSalary> findAllEmployee (){
        return employeeRepository.findAllBy();
    }

    public EmployeeDTO findEmployeeById (Long id){
        return employeeMapper.toDto(employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(MESSAGE_EMPLOYEE_NOT_FOUND, id))));
    }

    public EmployeeDTO createEmployee (EmployeeDTO dto){

        Employee employee = employeeMapper.toEntity(dto);

        Long departmentId = dto.getDepartmentId();
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Департамент с id %d не найден", departmentId)));

        employee.setDepartment(department);

        Employee saved = employeeRepository.save(employee);
        log.info("Сотрудник был создан: {}", saved);
        return employeeMapper.toDto(saved);
    }

    public EmployeeDTO updateEmployee (Long employeeId, EmployeeDTO dto){

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(MESSAGE_EMPLOYEE_NOT_FOUND, employeeId)));

        employeeMapper.updateEntity(dto, employee);

        Long departmentId = dto.getDepartmentId();
        if (departmentId != null) {
            Department department = departmentRepository.findById(departmentId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            String.format("Департамент с id %d не найден", departmentId)
                    ));
            employee.setDepartment(department);
        }

        Employee saved = employeeRepository.save(employee);
        log.info("Сотрудник был обновлен: {}", saved);
        return employeeMapper.toDto(saved);
    }

    public void deleteEmployee (Long id){
        if (!employeeRepository.existsById(id)){
            throw new ResourceNotFoundException(String.format(MESSAGE_EMPLOYEE_NOT_FOUND, id));
        }
        employeeRepository.deleteById(id);
        log.info("Сотрудник с id {} был удален", id);
    }
}
