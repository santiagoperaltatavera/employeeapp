package com.parameta.employeeapp.infrastructure.soap.mapper;

import com.parameta.employeeapp.application.mappers.EmployeeSoapMapper;
import com.parameta.employeeapp.domain.model.Employee;
import com.parameta.employeeapp.infrastructure.soap.dto.SoapEmployeeDTO;
import com.parameta.employeeapp.shared.utils.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSoapMapperImpl implements EmployeeSoapMapper<SoapEmployeeDTO, SoapEmployeeDTO> {

    public SoapEmployeeDTO toSoapDTO(Employee employee) {
        return new SoapEmployeeDTO(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDocumentType(),
                employee.getDocumentNumber(),
                DateUtils.toXMLGregorianCalendar(employee.getDateOfBirth()),
                DateUtils.toXMLGregorianCalendar(employee.getHireDate()),
                employee.getPosition(),
                employee.getSalary()
        );
    }

    public Employee toEmployee(SoapEmployeeDTO dto) {
        return Employee.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .documentType(dto.getDocumentType())
                .documentNumber(dto.getDocumentNumber())
                .dateOfBirth(dto.getDateOfBirth())
                .hireDate(dto.getHireDate())
                .position(dto.getPosition())
                .salary(dto.getSalary())
                .build();
    }

}
