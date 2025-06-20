package com.parameta.employeeapp.infrastructure.rest.mapper;

import com.parameta.employeeapp.application.mappers.EmployeeMapper;
import com.parameta.employeeapp.domain.model.Employee;
import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeRequestDTO;
import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeResponseDTO;
import com.parameta.employeeapp.shared.utils.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapperImpl implements EmployeeMapper<EmployeeRequestDTO, EmployeeResponseDTO> {

    @Override
    public Employee toEmployee(EmployeeRequestDTO dto) {
        return Employee.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .documentType(dto.documentType())
                .documentNumber(dto.documentNumber())
                .dateOfBirth(dto.dateOfBirth())
                .hireDate(dto.hireDate())
                .position(dto.position())
                .salary(dto.salary())
                .build();
    }

    @Override
    public EmployeeResponseDTO toResponseDTO(Employee employee, String age, String timeInCompany) {
        return new EmployeeResponseDTO(
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
