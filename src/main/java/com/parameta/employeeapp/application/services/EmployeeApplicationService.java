package com.parameta.employeeapp.application.services;

import com.parameta.employeeapp.application.ports.out.EmployeePort;
import com.parameta.employeeapp.domain.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeApplicationService {
    private final EmployeePort employeePort;

    public EmployeeApplicationService(EmployeePort employeePort) {
        this.employeePort = employeePort;
    }

    public void createEmployee(Employee employee) {
        employeePort.save(employee);
    }
}
