package com.parameta.employeeapp.infrastructure.soap.adapter;

import com.parameta.employeeapp.application.ports.out.EmployeePort;
import com.parameta.employeeapp.domain.model.Employee;
import com.parameta.employeeapp.infrastructure.database.mapper.EmployeeEntityMapperImpl;
import com.parameta.employeeapp.infrastructure.database.repository.EmployeeEntity;
import com.parameta.employeeapp.infrastructure.database.repository.EmployeeRepositoryImpl;
import org.springframework.stereotype.Component;

@Component
public class EmployeeAdapter implements EmployeePort {

    private final EmployeeRepositoryImpl employeeRepository;
    private final EmployeeEntityMapperImpl employeeEntityMapper;

    public EmployeeAdapter(EmployeeRepositoryImpl employeeRepository,
                           EmployeeEntityMapperImpl employeeEntityMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeEntityMapper = employeeEntityMapper;
    }

    @Override
    public void save(Employee employee) {
        EmployeeEntity entity = employeeEntityMapper.toEntity(employee);
        employeeRepository.save(entity);
    }
}
