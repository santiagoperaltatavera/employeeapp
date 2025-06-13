package com.parameta.employeeapp.infrastructure.soap.dto;

import com.parameta.employeeapp.application.dtos.EmployeeDTO;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;

@XmlRootElement(name = "Employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoapEmployeeDTO implements EmployeeDTO {

    @XmlElement(required = true)
    private String firstName;

    @XmlElement(required = true)
    private String lastName;

    @XmlElement(required = true)
    private String documentType;

    @XmlElement(required = true)
    private String documentNumber;

    @XmlElement(required = true)
    private XMLGregorianCalendar dateOfBirth;

    @XmlElement(required = true)
    private XMLGregorianCalendar hireDate;

    @XmlElement(required = true)
    private String position;

    @XmlElement(required = true)
    private Double salary;

    public SoapEmployeeDTO() {
    }

    public SoapEmployeeDTO(String firstName, String lastName, String documentType, String documentNumber,
                           XMLGregorianCalendar dateOfBirth, XMLGregorianCalendar hireDate, String position, Double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.dateOfBirth = dateOfBirth;
        this.hireDate = hireDate;
        this.position = position;
        this.salary = salary;
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

    @Override
    public LocalDate getDateOfBirth() {
        return dateOfBirth.toGregorianCalendar().toZonedDateTime().toLocalDate();
    }

    @Override
    public LocalDate getHireDate() {
        return hireDate.toGregorianCalendar().toZonedDateTime().toLocalDate();
    }

    public String getPosition() {
        return position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setDateOfBirth(XMLGregorianCalendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setHireDate(XMLGregorianCalendar hireDate) {
        this.hireDate = hireDate;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
