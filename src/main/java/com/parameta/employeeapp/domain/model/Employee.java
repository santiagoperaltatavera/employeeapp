package com.parameta.employeeapp.domain.model;

import java.time.LocalDate;

public class Employee {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String documentType;
    private final String documentNumber;
    private final LocalDate dateOfBirth;
    private final LocalDate hireDate;
    private final String position;
    private final Double salary;

    private Employee(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.documentType = builder.documentType;
        this.documentNumber = builder.documentNumber;
        this.dateOfBirth = builder.dateOfBirth;
        this.hireDate = builder.hireDate;
        this.position = builder.position;
        this.salary = builder.salary;
    }
    
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", documentType='" + documentType + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", hireDate=" + hireDate +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public String getPosition() {
        return position;
    }

    public Double getSalary() {
        return salary;
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String documentType;
        private String documentNumber;
        private LocalDate dateOfBirth;
        private LocalDate hireDate;
        private String position;
        private Double salary;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder documentType(String documentType) {
            this.documentType = documentType;
            return this;
        }

        public Builder documentNumber(String documentNumber) {
            this.documentNumber = documentNumber;
            return this;
        }

        public Builder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder hireDate(LocalDate hireDate) {
            this.hireDate = hireDate;
            return this;
        }

        public Builder position(String position) {
            this.position = position;
            return this;
        }

        public Builder salary(Double salary) {
            this.salary = salary;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}
