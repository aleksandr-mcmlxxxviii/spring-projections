package com.mvc.springprojections.controller;

import com.mvc.springprojections.dto.DepartmentDTO;
import com.mvc.springprojections.dto.markers.Create;
import com.mvc.springprojections.dto.markers.Update;
import com.mvc.springprojections.service.DepartmentService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mvc.springprojections.constants.ValidationConstants.MIN_ID;

@Validated
@RequiredArgsConstructor
@RequestMapping("/api/departments")
@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
        return ResponseEntity.ok(departmentService.findAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable @Min(MIN_ID) Long id){
        return ResponseEntity.ok(departmentService.findDepartmentById(id));
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@Validated(Create.class) @RequestBody DepartmentDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(departmentService.createDepartment(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable @Min(MIN_ID) Long id,
                                                          @Validated(Update.class) @RequestBody DepartmentDTO dto) {
        return  ResponseEntity.ok(departmentService.updateDepartment(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable @Min(MIN_ID) Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
