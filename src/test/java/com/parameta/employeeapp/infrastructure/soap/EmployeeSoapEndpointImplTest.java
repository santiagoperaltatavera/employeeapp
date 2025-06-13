package com.parameta.employeeapp.infrastructure.soap;

import com.parameta.employeeapp.application.mappers.EmployeeSoapMapper;
import com.parameta.employeeapp.application.services.EmployeeApplicationService;
import com.parameta.employeeapp.application.services.EmployeeApplicationServiceImpl;
import com.parameta.employeeapp.domain.model.Employee;
import com.parameta.employeeapp.infrastructure.soap.dto.SoapEmployeeDTO;
import com.parameta.employeeapp.infrastructure.soap.endpoint.EmployeeSoapEndpoint;
import com.parameta.employeeapp.infrastructure.soap.endpoint.EmployeeSoapEndpointImpl;
import com.parameta.employeeapp.infrastructure.soap.mapper.EmployeeSoapMapperImpl;
import com.parameta.employeeapp.shared.utils.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeSoapEndpointImplTest {

    private EmployeeSoapEndpoint employeeSoapEndpoint;

    @Mock
    private EmployeeApplicationService<Employee>  employeeApplicationService;

    @Mock
    private EmployeeSoapMapper<SoapEmployeeDTO, SoapEmployeeDTO> employeeSoapMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeSoapEndpoint = new EmployeeSoapEndpointImpl(employeeApplicationService, employeeSoapMapper);
    }

    @Test
    void testCreateEmployeeSuccess() {
        
        LocalDate localDateOfBirth = LocalDate.of(1990, 1, 1);
        LocalDate localHireDate = LocalDate.of(2020, 1, 1);

        XMLGregorianCalendar dateOfBirth = DateUtils.toXMLGregorianCalendar(localDateOfBirth);
        XMLGregorianCalendar hireDate = DateUtils.toXMLGregorianCalendar(localHireDate);
        
        SoapEmployeeDTO soapEmployeeDTO = new SoapEmployeeDTO();
        soapEmployeeDTO.setFirstName("John");
        soapEmployeeDTO.setLastName("Doe");
        soapEmployeeDTO.setDocumentType("ID");
        soapEmployeeDTO.setDocumentNumber("12345");
        soapEmployeeDTO.setDateOfBirth(dateOfBirth);
        soapEmployeeDTO.setHireDate(hireDate);
        soapEmployeeDTO.setPosition("Developer");
        soapEmployeeDTO.setSalary(5000.0);

        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .documentType("ID")
                .documentNumber("12345")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .hireDate(LocalDate.of(2020, 1, 1))
                .position("Developer")
                .salary(5000.0)
                .build();

        when(employeeSoapMapper.toEmployee(soapEmployeeDTO)).thenReturn(employee);

        employeeSoapEndpoint.createEmployee(soapEmployeeDTO);

        verify(employeeSoapMapper, times(1)).toEmployee(soapEmployeeDTO);
        verify(employeeApplicationService, times(1)).createEmployee(employee);
    }

    @Test
    void testCreateEmployeeFailure() {
        
        LocalDate localDateOfBirth = LocalDate.of(1990, 1, 1);
        LocalDate localHireDate = LocalDate.of(2020, 1, 1);

        XMLGregorianCalendar dateOfBirth = DateUtils.toXMLGregorianCalendar(localDateOfBirth);
        XMLGregorianCalendar hireDate = DateUtils.toXMLGregorianCalendar(localHireDate);

        SoapEmployeeDTO soapEmployeeDTO = new SoapEmployeeDTO();
        soapEmployeeDTO.setFirstName("John");
        soapEmployeeDTO.setLastName("Doe");
        soapEmployeeDTO.setDocumentType("ID");
        soapEmployeeDTO.setDocumentNumber("12345");
        soapEmployeeDTO.setDateOfBirth(dateOfBirth);
        soapEmployeeDTO.setHireDate(hireDate);
        soapEmployeeDTO.setPosition("Developer");
        soapEmployeeDTO.setSalary(5000.0);

        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .documentType("ID")
                .documentNumber("12345")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .hireDate(LocalDate.of(2020, 1, 1))
                .position("Developer")
                .salary(5000.0)
                .build();

        when(employeeSoapMapper.toEmployee(soapEmployeeDTO)).thenReturn(employee);
        doThrow(new RuntimeException("Error creating employee")).when(employeeApplicationService).createEmployee(employee);

        try {
            employeeSoapEndpoint.createEmployee(soapEmployeeDTO);
        } catch (RuntimeException e) {
            verify(employeeSoapMapper, times(1)).toEmployee(soapEmployeeDTO);
            verify(employeeApplicationService, times(1)).createEmployee(employee);
            assertEquals("Error creating employee", e.getMessage());
        }
    }
}
