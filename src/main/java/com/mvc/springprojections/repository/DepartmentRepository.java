package com.mvc.springprojections.repository;

import com.mvc.springprojections.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
