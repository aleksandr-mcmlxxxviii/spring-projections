package com.mvc.springprojections.controller;

import com.mvc.springprojections.dto.EmployeeDTO;
import com.mvc.springprojections.dto.markers.Create;
import com.mvc.springprojections.dto.markers.Update;
import com.mvc.springprojections.projection.EmployeeProjection;
import com.mvc.springprojections.projection.EmployeeProjectionNotSalary;
import com.mvc.springprojections.service.EmployeeServiceImpl;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mvc.springprojections.constants.ValidationConstants.*;

@Validated
@RequiredArgsConstructor
@RequestMapping("/api/employees")
@RestController
public class EmployeeController {

    private  final EmployeeServiceImpl employeeService;

    @GetMapping("/page")
    public ResponseEntity<Page<EmployeeProjection>> getPageEmployee (
            @PageableDefault(page = PAGE_DEFAULT, size = PAGE_SIZE_DEFAULT) Pageable pageable){

        return ResponseEntity.ok(employeeService.findEmployeePage(pageable));
    }

    @GetMapping("/all")
    public  ResponseEntity<List<EmployeeProjectionNotSalary>> getAllEmployees (){
        return ResponseEntity.ok(employeeService.findAllEmployee());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById (@PathVariable @Min(MIN_ID) Long id){
        return ResponseEntity.ok(employeeService.findEmployeeById(id));
    }

    @PostMapping("")
    public ResponseEntity<EmployeeDTO> createEmployee (@Validated(Create.class) @RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(employeeService.createEmployee(employeeDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee (
            @PathVariable @Min(MIN_ID)  Long id, @Validated(Update.class) @RequestBody EmployeeDTO employeeDTO){

        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable @Min(MIN_ID) Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
