package com.parameta.employeeapp.infrastructure.rest;

import com.parameta.employeeapp.application.mappers.EmployeeMapper;
import com.parameta.employeeapp.domain.model.Employee;
import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeRequestDTO;
import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeResponseDTO;
import com.parameta.employeeapp.infrastructure.rest.mapper.EmployeeMapperImpl;
import com.parameta.employeeapp.shared.utils.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperImplTest {

    private EmployeeMapper<EmployeeRequestDTO, EmployeeResponseDTO> employeeMapper;

    @BeforeEach
    void setUp() {
        employeeMapper = new EmployeeMapperImpl();
    }

    @Test
    void testToEmployeeSuccess() {
        EmployeeRequestDTO requestDTO = new EmployeeRequestDTO(
                "John", "Doe", "ID", "12345",
                LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1),
                "Developer", 5000.0
        );

        Employee employee = employeeMapper.toEmployee(requestDTO);

        assertNotNull(employee);
        assertEquals(requestDTO.firstName(), employee.getFirstName());
        assertEquals(requestDTO.lastName(), employee.getLastName());
        assertEquals(requestDTO.documentType(), employee.getDocumentType());
        assertEquals(requestDTO.documentNumber(), employee.getDocumentNumber());
        assertEquals(requestDTO.dateOfBirth(), employee.getDateOfBirth());
        assertEquals(requestDTO.hireDate(), employee.getHireDate());
        assertEquals(requestDTO.position(), employee.getPosition());
        assertEquals(requestDTO.salary(), employee.getSalary());
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

        EmployeeResponseDTO responseDTO = employeeMapper.toResponseDTO(employee, expectedAge, expectedTimeInCompany);

        assertNotNull(responseDTO);
        assertEquals(employee.getFirstName(), responseDTO.firstName());
        assertEquals(employee.getLastName(), responseDTO.lastName());
        assertEquals(employee.getDocumentType(), responseDTO.documentType());
        assertEquals(employee.getDocumentNumber(), responseDTO.documentNumber());
        assertEquals(DateUtils.formatDate(employee.getDateOfBirth()), responseDTO.dateOfBirth());
        assertEquals(DateUtils.formatDate(employee.getHireDate()), responseDTO.hireDate());
        assertEquals(employee.getPosition(), responseDTO.position());
        assertEquals(employee.getSalary(), responseDTO.salary());
        assertEquals(expectedAge, responseDTO.age());
        assertEquals(expectedTimeInCompany, responseDTO.timeInCompany());
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
