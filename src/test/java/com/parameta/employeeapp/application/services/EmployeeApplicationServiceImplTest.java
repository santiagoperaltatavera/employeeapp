package com.parameta.employeeapp.application.services;

import com.parameta.employeeapp.application.ports.out.EmployeePort;
import com.parameta.employeeapp.domain.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EmployeeApplicationServiceImplTest {

    @Mock
    private EmployeePort employeePort;

    private EmployeeApplicationService<Employee>  employeeApplicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeApplicationService = new EmployeeApplicationServiceImpl(employeePort);
    }

    @Test
    void testCreateEmployeeSuccess() {
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .documentType("ID")
                .documentNumber("12345")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .hireDate(LocalDate.of(2020, 1, 1))
                .position("Developer")
                .salary(5000.0)
                .build();

        employeeApplicationService.createEmployee(employee);

        verify(employeePort, times(1)).save(employee);
    }

    @Test
    void testCreateEmployeeFailure() {
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .documentType("ID")
                .documentNumber("12345")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .hireDate(LocalDate.of(2020, 1, 1))
                .position("Developer")
                .salary(5000.0)
                .build();

        doThrow(new RuntimeException("Error saving employee")).when(employeePort).save(employee);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> employeeApplicationService.createEmployee(employee));
        assertEquals("Error saving employee", exception.getMessage());

        verify(employeePort, times(1)).save(employee);
    }
}
