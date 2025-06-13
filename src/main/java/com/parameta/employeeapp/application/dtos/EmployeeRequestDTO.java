package com.parameta.employeeapp.application.dtos;

import java.time.LocalDate;

public interface EmployeeRequestDTO {
    String getFirstName();
    String getLastName();
    String getDocumentType();
    String getDocumentNumber();
    LocalDate getDateOfBirth();
    LocalDate getHireDate();
    String getPosition();
    Double getSalary();
}
