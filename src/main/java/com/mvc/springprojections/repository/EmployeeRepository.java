package com.mvc.springprojections.repository;

import com.mvc.springprojections.entity.Employee;
import com.mvc.springprojections.projection.EmployeeProjection;
import com.mvc.springprojections.projection.EmployeeProjectionNotSalary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    String query = "SELECT e.firstName as firstName, " +
            "e.lastName as lastName, " +
            "e.position as position, " +
            "d.name as departmentName " +
            "FROM Employee e JOIN e.department d";

    @Query(query)
    Page<EmployeeProjection> findAllBy(Pageable pageable);

    @Query(query)
    List<EmployeeProjectionNotSalary> findAllBy();

}
