package com.parameta.employeeapp.infrastructure.repository;

import com.parameta.employeeapp.infrastructure.database.repository.EmployeeEntity;
import com.parameta.employeeapp.infrastructure.database.repository.EmployeeRepositoryImpl;
import com.parameta.employeeapp.infrastructure.database.repository.JpaEmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EmployeeRepositoryImplTest {

    private EmployeeRepositoryImpl employeeRepository;

    @Mock
    private JpaEmployeeRepository jpaEmployeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeRepository = new EmployeeRepositoryImpl(jpaEmployeeRepository);
    }

    @Test
    void testSaveEmployeeSuccess() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName("John");
        employeeEntity.setLastName("Doe");
        employeeEntity.setDocumentType("ID");
        employeeEntity.setDocumentNumber("12345");
        employeeEntity.setDateOfBirth(LocalDate.of(1990, 1, 1));
        employeeEntity.setHireDate(LocalDate.of(2020, 1, 1));
        employeeEntity.setPosition("Developer");
        employeeEntity.setSalary(5000.0);

        employeeRepository.save(employeeEntity);

        verify(jpaEmployeeRepository, times(1)).save(employeeEntity);
    }

    @Test
    void testSaveEmployeeFailure() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName("John");
        employeeEntity.setLastName("Doe");
        employeeEntity.setDocumentType("ID");
        employeeEntity.setDocumentNumber("12345");
        employeeEntity.setDateOfBirth(LocalDate.of(1990, 1, 1));
        employeeEntity.setHireDate(LocalDate.of(2020, 1, 1));
        employeeEntity.setPosition("Developer");
        employeeEntity.setSalary(5000.0);

        doThrow(new RuntimeException("Error saving entity")).when(jpaEmployeeRepository).save(employeeEntity);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> employeeRepository.save(employeeEntity));
        assertEquals("Error saving entity", exception.getMessage());

        verify(jpaEmployeeRepository, times(1)).save(employeeEntity);
    }
}
