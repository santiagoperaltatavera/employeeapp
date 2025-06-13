package com.parameta.employeeapp.application.ports.out;

import com.parameta.employeeapp.domain.model.Employee;

public interface EmployeePort {
    void save(Employee employee);
}
