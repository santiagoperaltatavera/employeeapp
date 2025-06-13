package com.parameta.employeeapp.infrastructure.rest;

import com.parameta.employeeapp.domain.model.Employee;
import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeRequestDTOImpl;
import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeResponseDTOImpl;
import com.parameta.employeeapp.infrastructure.rest.mapper.EmployeeMapperImpl;
import com.parameta.employeeapp.shared.utils.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperImplTest {

    private EmployeeMapperImpl employeeMapper;

    @BeforeEach
    void setUp() {
        employeeMapper = new EmployeeMapperImpl();
    }

    @Test
    void testToEmployeeSuccess() {
        EmployeeRequestDTOImpl requestDTO = new EmployeeRequestDTOImpl(
                "John", "Doe", "ID", "12345",
                LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1),
                "Developer", 5000.0
        );

        Employee employee = employeeMapper.toEmployee(requestDTO);

        assertNotNull(employee);
        assertEquals(requestDTO.getFirstName(), employee.getFirstName());
        assertEquals(requestDTO.getLastName(), employee.getLastName());
        assertEquals(requestDTO.getDocumentType(), employee.getDocumentType());
        assertEquals(requestDTO.getDocumentNumber(), employee.getDocumentNumber());
        assertEquals(requestDTO.getDateOfBirth(), employee.getDateOfBirth());
        assertEquals(requestDTO.getHireDate(), employee.getHireDate());
        assertEquals(requestDTO.getPosition(), employee.getPosition());
        assertEquals(requestDTO.getSalary(), employee.getSalary());
    }

    @Test
    void testToResponseDTOSuccess() {
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

        String expectedAge = DateUtils.formatPeriod(DateUtils.calculatePeriod(employee.getDateOfBirth(), LocalDate.now()));
        String expectedTimeInCompany = DateUtils.formatPeriod(DateUtils.calculatePeriod(employee.getHireDate(), LocalDate.now()));

        EmployeeResponseDTOImpl responseDTO = employeeMapper.toResponseDTO(employee, expectedAge, expectedTimeInCompany);

        assertNotNull(responseDTO);
        assertEquals(employee.getFirstName(), responseDTO.getFirstName());
        assertEquals(employee.getLastName(), responseDTO.getLastName());
        assertEquals(employee.getDocumentType(), responseDTO.getDocumentType());
        assertEquals(employee.getDocumentNumber(), responseDTO.getDocumentNumber());
        assertEquals(DateUtils.formatDate(employee.getDateOfBirth()), responseDTO.getDateOfBirth());
        assertEquals(DateUtils.formatDate(employee.getHireDate()), responseDTO.getHireDate());
        assertEquals(employee.getPosition(), responseDTO.getPosition());
        assertEquals(employee.getSalary(), responseDTO.getSalary());
        assertEquals(expectedAge, responseDTO.getAge());
        assertEquals(expectedTimeInCompany, responseDTO.getTimeInCompany());
    }

    @Test
    void testToEmployeeFailureWithNullInput() {
        assertThrows(NullPointerException.class, () -> employeeMapper.toEmployee(null));
    }

    @Test
    void testToResponseDTOFailureWithNullInput() {
        assertThrows(NullPointerException.class, () -> employeeMapper.toResponseDTO(null, "30 years", "3 years"));
    }
}
