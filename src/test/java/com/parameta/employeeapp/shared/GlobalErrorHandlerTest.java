package com.parameta.employeeapp.shared;

import com.parameta.employeeapp.application.services.EmployeeService;
import com.parameta.employeeapp.infrastructure.rest.EmployeeController;
import com.parameta.employeeapp.shared.error.ErrorResponse;
import com.parameta.employeeapp.shared.error.GlobalErrorHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.WebRequest;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class GlobalErrorHandlerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        EmployeeController employeeController = new EmployeeController(employeeService);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
                .setControllerAdvice(new GlobalErrorHandler())
                .build();
    }

    @Test
    void testHandleDateTimeParseException() throws Exception {
        when(employeeService.createEmployee(any())).thenThrow(new DateTimeParseException("Invalid date format", "2023-13-01", 0));

        mockMvc.perform(get("/employees")
                        .param("firstName", "John")
                        .param("lastName", "Doe")
                        .param("documentType", "ID")
                        .param("documentNumber", "12345")
                        .param("dateOfBirth", "2023-13-01")
                        .param("hireDate", "2020-01-01")
                        .param("position", "Developer")
                        .param("salary", "5000.0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value("Invalid date format: 2023-13-01"))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.path").exists());
    }

    @Test
    void testHandleIllegalArgumentException() throws Exception {
        when(employeeService.createEmployee(any())).thenThrow(new IllegalArgumentException("Test IllegalArgumentException"));

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
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value("Test IllegalArgumentException"))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.path").exists());
    }

    @Test
    void testHandleNullPointerException() throws Exception {
        when(employeeService.createEmployee(any())).thenThrow(new NullPointerException("Test NullPointerException"));

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
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.statusCode").value(422))
                .andExpect(jsonPath("$.message").value("A required value was null: Test NullPointerException"))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.path").exists());
    }

    @Test
    void testHandleUnsupportedOperationException() throws Exception {
        when(employeeService.createEmployee(any())).thenThrow(new UnsupportedOperationException("Test UnsupportedOperationException"));

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
                .andExpect(status().isNotImplemented())
                .andExpect(jsonPath("$.statusCode").value(501))
                .andExpect(jsonPath("$.message").value("Operation not supported: Test UnsupportedOperationException"))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.path").exists());
    }
    
    
}
