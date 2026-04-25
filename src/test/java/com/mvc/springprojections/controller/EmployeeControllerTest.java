package com.mvc.springprojections.controller;

import com.mvc.springprojections.SpringProjectionsApplicationTests;
import com.mvc.springprojections.dto.DepartmentDTO;
import com.mvc.springprojections.dto.EmployeeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class EmployeeControllerTest extends SpringProjectionsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Long existingDepartmentId;

    @BeforeEach
    void setUp() throws Exception {
        DepartmentDTO department = new DepartmentDTO();
        department.setName("IT Department");

        String response = mockMvc.perform(post("/api/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(department)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        DepartmentDTO createdDepartment = objectMapper.readValue(response, DepartmentDTO.class);
        existingDepartmentId = createdDepartment.getId();

        System.out.println("Created department with ID: " + existingDepartmentId);
    }

    @Test
    void shouldCreateEmployee() throws Exception {
        EmployeeDTO employee = new EmployeeDTO();
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        employee.setPosition("Java Developer");
        employee.setSalary(BigDecimal.valueOf(5000.00));
        employee.setDepartmentId(existingDepartmentId);

        String json = objectMapper.writeValueAsString(employee);
        System.out.println("Request JSON: " + json);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(result -> System.out.println("Response: " + result.getResponse().getContentAsString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value("Иван"))
                .andExpect(jsonPath("$.lastName").value("Иванов"))
                .andExpect(jsonPath("$.position").value("Java Developer"))
                .andExpect(jsonPath("$.salary").value(5000.00))
                .andExpect(jsonPath("$.departmentId").value(existingDepartmentId));
    }

    @Test
    void shouldReturnBadRequestWhenDepartmentNotExists() throws Exception {
        EmployeeDTO employee = new EmployeeDTO();
        employee.setFirstName("Петр");
        employee.setLastName("Петров");
        employee.setPosition("Developer");
        employee.setSalary(BigDecimal.valueOf(5000.00));
        employee.setDepartmentId(999L);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequestWhenEmployeeDataInvalid() throws Exception {
        EmployeeDTO employee = new EmployeeDTO();
        employee.setFirstName("");
        employee.setLastName("Петров");
        employee.setPosition("Developer");
        employee.setSalary(BigDecimal.valueOf(-100));
        employee.setDepartmentId(existingDepartmentId);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isBadRequest());
    }
}
