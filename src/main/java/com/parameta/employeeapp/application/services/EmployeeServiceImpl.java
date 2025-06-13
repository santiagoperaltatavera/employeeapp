package com.parameta.employeeapp.application.services;

import com.parameta.employeeapp.application.mappers.EmployeeMapper;
import com.parameta.employeeapp.application.ports.out.EmployeeSoapClientPort;
import com.parameta.employeeapp.application.validators.EmployeeValidator;
import com.parameta.employeeapp.domain.model.Employee;
import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeRequestDTOImpl;
import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeResponseDTOImpl;
import com.parameta.employeeapp.shared.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class EmployeeServiceImpl implements EmployeeService<EmployeeRequestDTOImpl, EmployeeResponseDTOImpl> {

    private final EmployeeSoapClientPort<Employee> employeeSoapClientPort;
    private final EmployeeMapper<EmployeeRequestDTOImpl, EmployeeResponseDTOImpl> employeeMapper;
    private final EmployeeValidator<EmployeeRequestDTOImpl> employeeValidator;

    public EmployeeServiceImpl(EmployeeSoapClientPort<Employee>  employeeSoapClientPort,
                               EmployeeMapper<EmployeeRequestDTOImpl, EmployeeResponseDTOImpl> employeeMapper,
                               EmployeeValidator<EmployeeRequestDTOImpl>  employeeValidator) {
        this.employeeSoapClientPort = employeeSoapClientPort;
        this.employeeMapper = employeeMapper;
        this.employeeValidator = employeeValidator;
    }

    @Transactional
    public EmployeeResponseDTOImpl createEmployee(EmployeeRequestDTOImpl requestDTO) {
        employeeValidator.validate(requestDTO);

        Employee employee = employeeMapper.toEmployee(requestDTO);

        employeeSoapClientPort.sendEmployeeToSoap(employee);

        String age = DateUtils.formatPeriod(DateUtils.calculatePeriod(employee.getDateOfBirth(), LocalDate.now()));
        String timeInCompany = DateUtils.formatPeriod(DateUtils.calculatePeriod(employee.getHireDate(), LocalDate.now()));

        return employeeMapper.toResponseDTO(employee, age, timeInCompany);
    }
}

