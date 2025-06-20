package com.parameta.employeeapp.application.services;

import com.parameta.employeeapp.application.mappers.EmployeeMapper;
import com.parameta.employeeapp.application.ports.out.EmployeeSoapClientPort;
import com.parameta.employeeapp.application.validators.EmployeeValidator;
import com.parameta.employeeapp.domain.model.Employee;
import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeRequestDTO;
import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeResponseDTO;
import com.parameta.employeeapp.shared.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class EmployeeServiceImpl implements EmployeeService<EmployeeRequestDTO, EmployeeResponseDTO> {

    private final EmployeeSoapClientPort<Employee> employeeSoapClientPort;
    private final EmployeeMapper<EmployeeRequestDTO, EmployeeResponseDTO> employeeMapper;
    private final EmployeeValidator<EmployeeRequestDTO> employeeValidator;

    public EmployeeServiceImpl(EmployeeSoapClientPort<Employee>  employeeSoapClientPort,
                               EmployeeMapper<EmployeeRequestDTO, EmployeeResponseDTO> employeeMapper,
                               EmployeeValidator<EmployeeRequestDTO>  employeeValidator) {
        this.employeeSoapClientPort = employeeSoapClientPort;
        this.employeeMapper = employeeMapper;
        this.employeeValidator = employeeValidator;
    }

    @Transactional
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO) {
        employeeValidator.validate(requestDTO);

        Employee employee = employeeMapper.toEmployee(requestDTO);

        employeeSoapClientPort.sendEmployeeToSoap(employee);

        String age = DateUtils.formatPeriod(DateUtils.calculatePeriod(employee.getDateOfBirth(), LocalDate.now()));
        String timeInCompany = DateUtils.formatPeriod(DateUtils.calculatePeriod(employee.getHireDate(), LocalDate.now()));

        return employeeMapper.toResponseDTO(employee, age, timeInCompany);
    }
}

