package com.parameta.employeeapp.application.validators;

import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeRequestDTOImpl;
import com.parameta.employeeapp.shared.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EmployeeValidator {

    public void validate(EmployeeRequestDTOImpl dto) {
        LocalDate today = LocalDate.now();

        if (dto.getDateOfBirth().isAfter(today)) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }

        if (dto.getHireDate().isBefore(dto.getDateOfBirth())) {
            throw new IllegalArgumentException("Hire date cannot be before date of birth");
        }

        if (dto.getHireDate().isAfter(today)) {
            throw new IllegalArgumentException("Hire date cannot be in the future");
        }

        if (DateUtils.calculatePeriod(dto.getDateOfBirth(), today).getYears() < 18) {
            throw new IllegalArgumentException("Employee must be at least 18 years old");
        }
    }
}
