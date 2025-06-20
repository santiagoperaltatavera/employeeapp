package com.parameta.employeeapp.infrastructure.rest.dto;


import java.time.LocalDate;

public class EmployeeResponseDTOImpl {

    private final String firstName;
    private final String lastName;
    private final String documentType;
    private final String documentNumber;
    private final String dateOfBirth;
    private final String hireDate;
    private final String position;
    private final Double salary;
    private final String age;
    private final String timeInCompany;

    public EmployeeResponseDTOImpl(String firstName, String lastName, String documentType, String documentNumber,
                                   String dateOfBirth, String hireDate, String position, Double salary,
                                   String age, String timeInCompany) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.dateOfBirth = dateOfBirth;
        this.hireDate = hireDate;
        this.position = position;
        this.salary = salary;
        this.age = age;
        this.timeInCompany = timeInCompany;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getDocumentType() {
        return documentType;
    }

    @Override
    public String getDocumentNumber() {
        return documentNumber;
    }

    @Override
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String getHireDate() {
        return hireDate;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public Double getSalary() {
        return salary;
    }

    @Override
    public String getAge() {
        return age;
    }

    @Override
    public String getTimeInCompany() {
        return timeInCompany;
    }
}
