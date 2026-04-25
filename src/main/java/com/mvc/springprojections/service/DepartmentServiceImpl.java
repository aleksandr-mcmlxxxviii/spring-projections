package com.mvc.springprojections.service;

import com.mvc.springprojections.dto.DepartmentDTO;
import com.mvc.springprojections.entity.Department;
import com.mvc.springprojections.exception.ResourceNotFoundException;
import com.mvc.springprojections.mapper.DepartmentMapper;
import com.mvc.springprojections.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    private static final String MESSAGE_DEPARTMENT_NOT_FOUND = "Департамент с ID %d не найден";

    @Override
    public List<DepartmentDTO> findAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toDto)
                .toList();
    }

    @Override
    public DepartmentDTO findDepartmentById(Long id) {
        return departmentMapper.toDto(departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(MESSAGE_DEPARTMENT_NOT_FOUND, id))));
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO dto) {
        Department saved = departmentRepository.save(departmentMapper.toEntity(dto));
        log.info("Департамент был создан: {}", saved);

        return departmentMapper.toDto(saved);
    }

    @Override
    public DepartmentDTO updateDepartment(Long id, DepartmentDTO dto) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(MESSAGE_DEPARTMENT_NOT_FOUND, id)));

        departmentMapper.updateEntity(dto, department);

        Department saved = departmentRepository.save(department);
        log.info("Департамент был обновлен: {}", saved);
        return departmentMapper.toDto(saved);
    }

    @Override
    public void deleteDepartment(Long id) {
        if(!departmentRepository.existsById(id)){
            throw new ResourceNotFoundException(String.format(MESSAGE_DEPARTMENT_NOT_FOUND, id));
        }
        departmentRepository.deleteById(id);
        log.info("Департамент c ID {} был удален", id);
    }
}
