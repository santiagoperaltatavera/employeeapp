package com.parameta.employeeapp.infrastructure.soap;

import com.parameta.employeeapp.domain.model.Employee;
import com.parameta.employeeapp.infrastructure.soap.dto.SoapEmployeeDTO;
import com.parameta.employeeapp.infrastructure.soap.mapper.EmployeeSoapMapperImpl;
import com.parameta.employeeapp.shared.utils.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeSoapMapperImplTest {

    private EmployeeSoapMapperImpl employeeSoapMapper;
    

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeSoapMapper = new EmployeeSoapMapperImpl();
    }

    @Test
    void testToSoapDTOSuccess() {
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        LocalDate hireDate = LocalDate.of(2020, 1, 1);
        XMLGregorianCalendar xmlDateOfBirth = DateUtils.toXMLGregorianCalendar(dateOfBirth);
        XMLGregorianCalendar xmlHireDate = DateUtils.toXMLGregorianCalendar(hireDate);

        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .documentType("ID")
                .documentNumber("12345")
                .dateOfBirth(dateOfBirth)
                .hireDate(hireDate)
                .position("Developer")
                .salary(5000.0)
                .build();

        SoapEmployeeDTO soapEmployeeDTO = employeeSoapMapper.toSoapDTO(employee);

        assertNotNull(soapEmployeeDTO);
        assertEquals(employee.getFirstName(), soapEmployeeDTO.getFirstName());
        assertEquals(employee.getLastName(), soapEmployeeDTO.getLastName());
        assertEquals(employee.getDocumentType(), soapEmployeeDTO.getDocumentType());
        assertEquals(employee.getDocumentNumber(), soapEmployeeDTO.getDocumentNumber());
        assertEquals(dateOfBirth, soapEmployeeDTO.getDateOfBirth());
        assertEquals(hireDate, soapEmployeeDTO.getHireDate());
        assertEquals(employee.getPosition(), soapEmployeeDTO.getPosition());
        assertEquals(employee.getSalary(), soapEmployeeDTO.getSalary());
    }

    @Test
    void testToEmployeeSuccess() {
        XMLGregorianCalendar xmlDateOfBirth = DateUtils.toXMLGregorianCalendar(LocalDate.of(1990, 1, 1));
        XMLGregorianCalendar xmlHireDate = DateUtils.toXMLGregorianCalendar(LocalDate.of(2020, 1, 1));

        SoapEmployeeDTO soapEmployeeDTO = new SoapEmployeeDTO(
                "John",
                "Doe",
                "ID",
                "12345",
                xmlDateOfBirth,
                xmlHireDate,
                "Developer",
                5000.0
        );

        Employee employee = employeeSoapMapper.toEmployee(soapEmployeeDTO);

        assertNotNull(employee);
        assertEquals(soapEmployeeDTO.getFirstName(), employee.getFirstName());
        assertEquals(soapEmployeeDTO.getLastName(), employee.getLastName());
        assertEquals(soapEmployeeDTO.getDocumentType(), employee.getDocumentType());
        assertEquals(soapEmployeeDTO.getDocumentNumber(), employee.getDocumentNumber());
        assertEquals(soapEmployeeDTO.getDateOfBirth(), employee.getDateOfBirth());
        assertEquals(soapEmployeeDTO.getHireDate(), employee.getHireDate());
        assertEquals(soapEmployeeDTO.getPosition(), employee.getPosition());
        assertEquals(soapEmployeeDTO.getSalary(), employee.getSalary());
    }
}
