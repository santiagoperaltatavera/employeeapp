package com.parameta.employeeapp.infrastructure.soap.endpoint;

import com.parameta.employeeapp.application.mappers.EmployeeSoapMapper;
import com.parameta.employeeapp.application.services.EmployeeApplicationService;
import com.parameta.employeeapp.application.services.EmployeeApplicationServiceImpl;
import com.parameta.employeeapp.domain.model.Employee;
import com.parameta.employeeapp.infrastructure.soap.dto.SoapEmployeeDTO;
import com.parameta.employeeapp.infrastructure.soap.mapper.EmployeeSoapMapperImpl;
import jakarta.jws.WebService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@WebService(endpointInterface = "com.parameta.employeeapp.infrastructure.soap.endpoint.EmployeeSoapEndpoint")
@Component
public class EmployeeSoapEndpointImpl implements EmployeeSoapEndpoint {

    private final EmployeeApplicationService<Employee> employeeApplicationService;
    private final EmployeeSoapMapper<SoapEmployeeDTO, SoapEmployeeDTO> employeeSoapMapper;


    public EmployeeSoapEndpointImpl(EmployeeApplicationService<Employee> employeeApplicationServiceImpl,
                                    EmployeeSoapMapper<SoapEmployeeDTO, SoapEmployeeDTO> employeeSoapMapper) {
        this.employeeApplicationService = employeeApplicationServiceImpl;
        this.employeeSoapMapper = employeeSoapMapper;
    }

    @Override
    @Transactional
    public void createEmployee(SoapEmployeeDTO employeeDTO) {
        Employee employee = employeeSoapMapper.toEmployee(employeeDTO);
        employeeApplicationService.createEmployee(employee);
    }
}
