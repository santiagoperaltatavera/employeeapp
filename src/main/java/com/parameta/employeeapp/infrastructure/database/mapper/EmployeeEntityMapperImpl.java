package com.parameta.employeeapp.infrastructure.database.mapper;

import com.parameta.employeeapp.domain.model.Employee;
import com.parameta.employeeapp.infrastructure.database.repository.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEntityMapperImpl implements EmployeeEntityMapper<Employee, EmployeeEntity> {

    @Override
    public EmployeeEntity toEntity(Employee employee) {
        if (employee == null) {
            return null;
        }
        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(employee.getId());
        entity.setFirstName(employee.getFirstName());
        entity.setLastName(employee.getLastName());
        entity.setDocumentType(employee.getDocumentType());
        entity.setDocumentNumber(employee.getDocumentNumber());
        entity.setDateOfBirth(employee.getDateOfBirth());
        entity.setHireDate(employee.getHireDate());
        entity.setPosition(employee.getPosition());
        entity.setSalary(employee.getSalary());
        return entity;
    }

    @Override
    public Employee toDomain(EmployeeEntity entity) {
        if (entity == null) {
            return null;
        }
        return Employee.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .documentType(entity.getDocumentType())
                .documentNumber(entity.getDocumentNumber())
                .dateOfBirth(entity.getDateOfBirth())
                .hireDate(entity.getHireDate())
                .position(entity.getPosition())
                .salary(entity.getSalary())
                .build();
    }
}
