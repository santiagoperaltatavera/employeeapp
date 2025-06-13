package com.parameta.employeeapp.infrastructure.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
