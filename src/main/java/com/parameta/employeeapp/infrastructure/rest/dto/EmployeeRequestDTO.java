package com.parameta.employeeapp.infrastructure.rest.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public class EmployeeRequestDTOImpl {

        @NotBlank(message = "First name is required")
        private final String firstName;

        @NotBlank(message = "Last name is required")
        private final String lastName;

        @NotBlank(message = "Document type is required")
        private final String documentType;

        @NotBlank(message = "Document number is required")
        private final String documentNumber;

        @NotNull(message = "Date of birth is required")
        @Past(message = "Date of birth must be in the past")
        private final LocalDate dateOfBirth;

        @NotNull(message = "Hire date is required")
        @PastOrPresent(message = "Hire date must be in the past or today")
        private final LocalDate hireDate;

        @NotBlank(message = "Position is required")
        private final String position;

        @NotNull(message = "Salary is required")
        @PositiveOrZero(message = "Salary must be positive or zero")
        private final Double salary;

        public EmployeeRequestDTOImpl(String firstName, String lastName, String documentType, String documentNumber,
                                      LocalDate dateOfBirth, LocalDate hireDate, String position, Double salary) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.documentType = documentType;
                this.documentNumber = documentNumber;
                this.dateOfBirth = dateOfBirth;
                this.hireDate = hireDate;
                this.position = position;
                this.salary = salary;
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
        public LocalDate getDateOfBirth() {
                return dateOfBirth;
        }

        @Override
        public LocalDate getHireDate() {
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
}
