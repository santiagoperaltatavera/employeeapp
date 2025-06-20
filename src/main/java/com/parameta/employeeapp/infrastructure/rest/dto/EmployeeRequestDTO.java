package com.parameta.employeeapp.infrastructure.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record EmployeeRequestDTO(
        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotBlank(message = "Document type is required")
        String documentType,

        @NotBlank(message = "Document number is required")
        String documentNumber,

        @NotNull(message = "Date of birth is required")
        @Past(message = "Date of birth must be in the past")
        LocalDate dateOfBirth,

        @NotNull(message = "Hire date is required")
        @PastOrPresent(message = "Hire date must be in the past or today")
        LocalDate hireDate,

        @NotBlank(message = "Position is required")
        String position,

        @NotNull(message = "Salary is required")
        @PositiveOrZero(message = "Salary must be positive or zero")
        Double salary
) {}
