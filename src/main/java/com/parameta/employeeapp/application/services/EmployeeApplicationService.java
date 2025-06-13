package com.parameta.employeeapp.application.services;

public interface EmployeeApplicationService<T> {
    void createEmployee(T employee);
}
