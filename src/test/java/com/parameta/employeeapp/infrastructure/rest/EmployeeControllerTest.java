package com.parameta.employeeapp.infrastructure.rest;

import com.parameta.employeeapp.application.services.EmployeeService;
import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeRequestDTO;
import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeResponseDTO;
import com.parameta.employeeapp.shared.error.GlobalErrorHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService<EmployeeRequestDTO, EmployeeResponseDTO> employeeService;

    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        employeeController = new EmployeeController(employeeService);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).setControllerAdvice(new GlobalErrorHandler()).build();
    }

    @Test
    void testCreateEmployeeServiceException() throws Exception {
        when(employeeService.createEmployee(any())).thenThrow(new RuntimeException("Service error"));

        mockMvc.perform(get("/employees")
                        .param("firstName", "John")
                        .param("lastName", "Doe")
                        .param("documentType", "ID")
                        .param("documentNumber", "12345")
                        .param("dateOfBirth", "1990-01-01")
                        .param("hireDate", "2020-01-01")
                        .param("position", "Developer")
                        .param("salary", "5000.0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.statusCode").value(500))
                .andExpect(jsonPath("$.message").value("Service error"))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.path").exists());
    }

    @Test
    void testCreateEmployeeInvalidParameter() throws Exception {
        mockMvc.perform(get("/employees")
                        .param("firstName", "John")
                        .param("lastName", "Doe")
                        .param("documentType", "ID")
                        .param("documentNumber", "12345")
                        .param("dateOfBirth", "invalid-date")
                        .param("hireDate", "2020-01-01")
                        .param("position", "Developer")
                        .param("salary", "5000.0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.path").exists());
    }

    @Test
    void testCreateEmployeeMissingParameters() throws Exception {
        mockMvc.perform(get("/employees")
                        .param("firstName", "John")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value("Missing required parameters: lastName"))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.path").exists());
    }

    @Test
    void testCreateEmployeeBoundaryValues() throws Exception {
        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO(
                "John", "Doe", "ID", "12345",
                "1900-01-01", "2020-01-01", "Developer", 1000000.0,
                "123 years", "3 years"
        );

        when(employeeService.createEmployee(any())).thenReturn(responseDTO);

        mockMvc.perform(get("/employees")
                        .param("firstName", "John")
                        .param("lastName", "Doe")
                        .param("documentType", "ID")
                        .param("documentNumber", "12345")
                        .param("dateOfBirth", "1900-01-01")
                        .param("hireDate", "2020-01-01")
                        .param("position", "Developer")
                        .param("salary", "1000000.0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salary").value(1000000.0))
                .andExpect(jsonPath("$.dateOfBirth").value("1900-01-01"));
    }
}
