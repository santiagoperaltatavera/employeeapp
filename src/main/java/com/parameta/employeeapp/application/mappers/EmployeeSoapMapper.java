package com.parameta.employeeapp.application.mappers;

import com.parameta.employeeapp.domain.model.Employee;

public interface EmployeeSoapMapper<T, R> {
    Employee toEmployee(T dto);
    R toSoapDTO(Employee employee);

    
}
