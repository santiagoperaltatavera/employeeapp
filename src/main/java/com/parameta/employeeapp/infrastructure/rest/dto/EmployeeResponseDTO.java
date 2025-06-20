package com.parameta.employeeapp.infrastructure.rest.dto;

public record EmployeeResponseDTO(
        String firstName,
        String lastName,
        String documentType,
        String documentNumber,
        String dateOfBirth,
        String hireDate,
        String position,
        Double salary,
        String age,
        String timeInCompany
) {}
