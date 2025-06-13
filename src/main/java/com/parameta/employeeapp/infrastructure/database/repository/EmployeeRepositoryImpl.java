package com.parameta.employeeapp.infrastructure.database.repository;

import com.parameta.employeeapp.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository<EmployeeEntity, Long> {

    private final JpaEmployeeRepository jpaEmployeeRepository;

    public EmployeeRepositoryImpl(JpaEmployeeRepository jpaEmployeeRepository) {
        this.jpaEmployeeRepository = jpaEmployeeRepository;
    }

    @Override
    public void save(EmployeeEntity entity) {
        jpaEmployeeRepository.save(entity);
    }
}
