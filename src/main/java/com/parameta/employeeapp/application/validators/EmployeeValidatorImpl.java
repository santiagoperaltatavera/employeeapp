package com.parameta.employeeapp.application.validators;

import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeRequestDTO;
import com.parameta.employeeapp.shared.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EmployeeValidatorImpl implements EmployeeValidator<EmployeeRequestDTO> {

    public void validate(EmployeeRequestDTO dto) {
        LocalDate today = LocalDate.now();

        if (dto.dateOfBirth().isAfter(today)) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }

        if (dto.hireDate().isBefore(dto.dateOfBirth())) {
            throw new IllegalArgumentException("Hire date cannot be before date of birth");
        }

        if (dto.hireDate().isAfter(today)) {
            throw new IllegalArgumentException("Hire date cannot be in the future");
        }

        if (DateUtils.calculatePeriod(dto.dateOfBirth(), today).getYears() < 18) {
            throw new IllegalArgumentException("Employee must be at least 18 years old");
        }
    }
}
