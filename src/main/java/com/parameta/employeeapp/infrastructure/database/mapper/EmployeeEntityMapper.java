package com.parameta.employeeapp.infrastructure.database.mapper;

public interface EmployeeEntityMapper<T, U> {
    U toEntity(T domainModel);
    T toDomain(U entity);
}
