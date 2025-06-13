package com.parameta.employeeapp.infrastructure.repository;

import com.parameta.employeeapp.domain.model.Employee;
import com.parameta.employeeapp.infrastructure.database.mapper.EmployeeEntityMapper;
import com.parameta.employeeapp.infrastructure.database.mapper.EmployeeEntityMapperImpl;
import com.parameta.employeeapp.infrastructure.database.repository.EmployeeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeEntityMapperImplTest {

    private EmployeeEntityMapper<Employee, EmployeeEntity> mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mapper = new EmployeeEntityMapperImpl();
    }

    @Test
    void testToEntity() {
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

        EmployeeEntity entity = mapper.toEntity(employee);

        assertNotNull(entity);
        assertEquals(employee.getId(), entity.getId());
        assertEquals(employee.getFirstName(), entity.getFirstName());
        assertEquals(employee.getLastName(), entity.getLastName());
        assertEquals(employee.getDocumentType(), entity.getDocumentType());
        assertEquals(employee.getDocumentNumber(), entity.getDocumentNumber());
        assertEquals(employee.getDateOfBirth(), entity.getDateOfBirth());
        assertEquals(employee.getHireDate(), entity.getHireDate());
        assertEquals(employee.getPosition(), entity.getPosition());
        assertEquals(employee.getSalary(), entity.getSalary());
    }

    @Test
    void testToDomain() {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(1L);
        entity.setFirstName("John");
        entity.setLastName("Doe");
        entity.setDocumentType("ID");
        entity.setDocumentNumber("12345");
        entity.setDateOfBirth(LocalDate.of(1990, 1, 1));
        entity.setHireDate(LocalDate.of(2020, 1, 1));
        entity.setPosition("Developer");
        entity.setSalary(5000.0);

        Employee employee = mapper.toDomain(entity);

        assertNotNull(employee);
        assertEquals(entity.getId(), employee.getId());
        assertEquals(entity.getFirstName(), employee.getFirstName());
        assertEquals(entity.getLastName(), employee.getLastName());
        assertEquals(entity.getDocumentType(), employee.getDocumentType());
        assertEquals(entity.getDocumentNumber(), employee.getDocumentNumber());
        assertEquals(entity.getDateOfBirth(), employee.getDateOfBirth());
        assertEquals(entity.getHireDate(), employee.getHireDate());
        assertEquals(entity.getPosition(), employee.getPosition());
        assertEquals(entity.getSalary(), employee.getSalary());
    }

    @Test
    void testToEntityWithNull() {
        EmployeeEntity entity = mapper.toEntity(null);
        assertNull(entity);
    }

    @Test
    void testToDomainWithNull() {
        Employee employee = mapper.toDomain(null);
        assertNull(employee);
    }
}
