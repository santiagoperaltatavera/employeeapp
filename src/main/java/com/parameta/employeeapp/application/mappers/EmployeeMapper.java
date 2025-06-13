package com.parameta.employeeapp.application.mappers;

import com.parameta.employeeapp.domain.model.Employee;

public interface EmployeeMapper<T, R> {
    Employee toEmployee(T requestDTO);
    R toResponseDTO(Employee employee, String age, String timeInCompany);
}
