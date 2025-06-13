package com.parameta.employeeapp.application.validators;

import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeRequestDTOImpl;

public interface EmployeeValidator<T> {
    void validate(T dto);
}
