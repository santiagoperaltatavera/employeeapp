package com.parameta.employeeapp.infrastructure.soap.endpoint;

import com.parameta.employeeapp.application.services.EmployeeApplicationService;
import com.parameta.employeeapp.domain.model.Employee;
import com.parameta.employeeapp.infrastructure.soap.dto.SoapEmployeeDTO;
import com.parameta.employeeapp.infrastructure.soap.mapper.EmployeeSoapMapperImpl;
import jakarta.jws.WebService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@WebService(endpointInterface = "com.parameta.employeeapp.infrastructure.soap.endpoint.EmployeeSoapEndpoint")
@Component
public class EmployeeSoapEndpointImpl implements EmployeeSoapEndpoint {

    private final EmployeeApplicationService employeeApplicationService;
    private final EmployeeSoapMapperImpl employeeSoapMapper;

    public EmployeeSoapEndpointImpl(EmployeeApplicationService employeeApplicationService,
                                    EmployeeSoapMapperImpl employeeSoapMapper) {
        this.employeeApplicationService = employeeApplicationService;
        this.employeeSoapMapper = employeeSoapMapper;
    }

    @Override
    @Transactional
    public void createEmployee(SoapEmployeeDTO employeeDTO) {
        Employee employee = employeeSoapMapper.toEmployee(employeeDTO);
        employeeApplicationService.createEmployee(employee);
    }
}
