package com.parameta.employeeapp.application.dtos;

import java.time.LocalDate;

public interface EmployeeDTO {
    String getFirstName();
    String getLastName();
    String getDocumentType();
    String getDocumentNumber();
    LocalDate getDateOfBirth();
    LocalDate getHireDate();
    String getPosition();
    Double getSalary();
}
