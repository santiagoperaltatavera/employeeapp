package com.parameta.employeeapp.application.ports.out;

import com.parameta.employeeapp.domain.model.Employee;

public interface EmployeeSoapClientPort<T> {
    void sendEmployeeToSoap(T entity);
}
