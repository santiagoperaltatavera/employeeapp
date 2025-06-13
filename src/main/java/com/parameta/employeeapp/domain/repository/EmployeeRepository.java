package com.parameta.employeeapp.domain.repository;

public interface EmployeeRepository<T, ID> {
    void save(T entity);
}
