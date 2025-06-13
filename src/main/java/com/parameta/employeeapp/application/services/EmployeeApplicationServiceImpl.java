package com.parameta.employeeapp.application.services;

import com.parameta.employeeapp.application.ports.out.EmployeePort;
import com.parameta.employeeapp.domain.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeApplicationServiceImpl implements EmployeeApplicationService<Employee> {

    private final EmployeePort employeePort;

    public EmployeeApplicationServiceImpl(EmployeePort employeePort) {
        this.employeePort = employeePort;
    }

    public void createEmployee(Employee employee) {
        employeePort.save(employee);
    }
}
