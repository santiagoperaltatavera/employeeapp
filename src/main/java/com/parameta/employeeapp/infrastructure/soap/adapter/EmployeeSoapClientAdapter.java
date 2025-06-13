package com.parameta.employeeapp.infrastructure.soap.adapter;

import com.parameta.employeeapp.application.ports.out.EmployeeSoapClientPort;
import com.parameta.employeeapp.domain.model.Employee;
import com.parameta.employeeapp.infrastructure.soap.dto.SoapEmployeeDTO;
import com.parameta.employeeapp.infrastructure.soap.endpoint.EmployeeSoapEndpoint;
import com.parameta.employeeapp.infrastructure.soap.mapper.EmployeeSoapMapperImpl;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSoapClientAdapter implements EmployeeSoapClientPort<Employee> {

    private final EmployeeSoapEndpoint employeeSoapEndpoint;
    private final EmployeeSoapMapperImpl employeeSoapMapper;

    public EmployeeSoapClientAdapter(EmployeeSoapEndpoint employeeSoapEndpoint,
                                     EmployeeSoapMapperImpl employeeSoapMapper) {
        this.employeeSoapEndpoint = employeeSoapEndpoint;
        this.employeeSoapMapper = employeeSoapMapper;
    }

    @Override
    public void sendEmployeeToSoap(Employee employee) {
        SoapEmployeeDTO soapEmployeeDTO = employeeSoapMapper.toSoapDTO(employee);
        employeeSoapEndpoint.createEmployee(soapEmployeeDTO);
    }
}
