package com.parameta.employeeapp.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testEmployeeBuilder() {
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

        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("ID", employee.getDocumentType());
        assertEquals("12345", employee.getDocumentNumber());
        assertEquals(LocalDate.of(1990, 1, 1), employee.getDateOfBirth());
        assertEquals(LocalDate.of(2020, 1, 1), employee.getHireDate());
        assertEquals("Developer", employee.getPosition());
        assertEquals(5000.0, employee.getSalary());
    }

    @Test
    void testToString() {
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .documentType("ID")
                .documentNumber("12345")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .hireDate(LocalDate.of(2020, 1, 1))
                .position("Developer")
                .salary(5000.0)
                .build();

        String expected = "Employee{id=1, firstName='John', lastName='Doe', documentType='ID', documentNumber='12345', dateOfBirth=1990-01-01, hireDate=2020-01-01, position='Developer', salary=5000.0}";
        assertEquals(expected, employee.toString());
    }
}
