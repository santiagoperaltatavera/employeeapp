package com.parameta.employeeapp.application.validators;

import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeValidatorImplTest {

    private EmployeeValidator<EmployeeRequestDTO> employeeValidator;

    @BeforeEach
    void setUp() {
        employeeValidator = new EmployeeValidatorImpl();
    }

    @Test
    void testValidateFutureDateOfBirth() {
        EmployeeRequestDTO dto = new EmployeeRequestDTO(
                "John", "Doe", "ID", "12345",
                LocalDate.of(2090, 1, 1), LocalDate.of(2020, 1, 1),
                "Developer", 5000.0
        );

        assertThrows(IllegalArgumentException.class, () -> employeeValidator.validate(dto),
                "Date of birth cannot be in the future");
    }

    @Test
    void testValidateHireDateBeforeDateOfBirth() {
        EmployeeRequestDTO dto = new EmployeeRequestDTO(
                "John", "Doe", "ID", "12345",
                LocalDate.of(1990, 1, 1), LocalDate.of(1980, 1, 1),
                "Developer", 5000.0
        );

        assertThrows(IllegalArgumentException.class, () -> employeeValidator.validate(dto),
                "Hire date cannot be before date of birth");
    }

    @Test
    void testValidateHireDateInFuture() {
        EmployeeRequestDTO dto = new EmployeeRequestDTO(
                "John", "Doe", "ID", "12345",
                LocalDate.of(1990, 1, 1), LocalDate.of(2090, 1, 1),
                "Developer", 5000.0
        );

        assertThrows(IllegalArgumentException.class, () -> employeeValidator.validate(dto),
                "Hire date cannot be in the future");
    }

    @Test
    void testValidateEmployeeUnder18YearsOld() {
        EmployeeRequestDTO dto = new EmployeeRequestDTO(
                "John", "Doe", "ID", "12345",
                LocalDate.of(LocalDate.now().getYear() - 17, 1, 1), LocalDate.of(2020, 1, 1),
                "Developer", 5000.0
        );

        assertThrows(IllegalArgumentException.class, () -> employeeValidator.validate(dto),
                "Employee must be at least 18 years old");
    }

    @Test
    void testValidateValidEmployee() {
        EmployeeRequestDTO dto = new EmployeeRequestDTO(
                "John", "Doe", "ID", "12345",
                LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1),
                "Developer", 5000.0
        );

        employeeValidator.validate(dto);
    }
}
