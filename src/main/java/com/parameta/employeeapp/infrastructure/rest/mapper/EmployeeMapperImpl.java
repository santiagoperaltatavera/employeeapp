package com.parameta.employeeapp.infrastructure.rest.mapper;

import com.parameta.employeeapp.application.mappers.EmployeeMapper;
import com.parameta.employeeapp.domain.model.Employee;
import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeRequestDTOImpl;
import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeResponseDTOImpl;
import com.parameta.employeeapp.shared.utils.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapperImpl implements EmployeeMapper<EmployeeRequestDTOImpl, EmployeeResponseDTOImpl> {

    @Override
    public Employee toEmployee(EmployeeRequestDTOImpl dto) {
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

    @Override
    public EmployeeResponseDTOImpl toResponseDTO(Employee employee, String age, String timeInCompany) {
        return new EmployeeResponseDTOImpl(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDocumentType(),
                employee.getDocumentNumber(),
                DateUtils.formatDate(employee.getDateOfBirth()),
                DateUtils.formatDate(employee.getHireDate()),
                employee.getPosition(),
                employee.getSalary(),
                age,
                timeInCompany
        );
    }
}
