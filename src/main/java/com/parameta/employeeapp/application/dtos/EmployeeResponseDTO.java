package com.parameta.employeeapp.application.dtos;

import java.time.LocalDate;

public interface EmployeeResponseDTO {
    String getFirstName();
    String getLastName();
    String getDocumentType();
    String getDocumentNumber();
    String getDateOfBirth();
    String getHireDate();
    String getPosition();
    Double getSalary();
    String getAge();
    String getTimeInCompany();
}
