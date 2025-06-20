package com.parameta.employeeapp.application.validators;

public interface EmployeeValidator<T> {
    void validate(T dto);
}
