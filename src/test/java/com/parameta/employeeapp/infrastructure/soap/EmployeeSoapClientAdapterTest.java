package com.parameta.employeeapp.infrastructure.soap;

import com.parameta.employeeapp.domain.model.Employee;
import com.parameta.employeeapp.infrastructure.soap.adapter.EmployeeSoapClientAdapter;
import com.parameta.employeeapp.infrastructure.soap.dto.SoapEmployeeDTO;
import com.parameta.employeeapp.infrastructure.soap.endpoint.EmployeeSoapEndpoint;
import com.parameta.employeeapp.infrastructure.soap.mapper.EmployeeSoapMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeSoapClientAdapterTest {

    private EmployeeSoapClientAdapter employeeSoapClientAdapter;

    @Mock
    private EmployeeSoapEndpoint employeeSoapEndpoint;

    @Mock
    private EmployeeSoapMapperImpl employeeSoapMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeSoapClientAdapter = new EmployeeSoapClientAdapter(employeeSoapEndpoint, employeeSoapMapper);
    }

    @Test
    void testSendEmployeeToSoapSuccess() {
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

        SoapEmployeeDTO soapEmployeeDTO = new SoapEmployeeDTO();
        when(employeeSoapMapper.toSoapDTO(employee)).thenReturn(soapEmployeeDTO);

        employeeSoapClientAdapter.sendEmployeeToSoap(employee);

        verify(employeeSoapMapper, times(1)).toSoapDTO(employee);
        verify(employeeSoapEndpoint, times(1)).createEmployee(soapEmployeeDTO);
    }

    @Test
    void testSendEmployeeToSoapFailure() {
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

        SoapEmployeeDTO soapEmployeeDTO = new SoapEmployeeDTO();
        when(employeeSoapMapper.toSoapDTO(employee)).thenReturn(soapEmployeeDTO);
        doThrow(new RuntimeException("SOAP endpoint error")).when(employeeSoapEndpoint).createEmployee(soapEmployeeDTO);

        try {
            employeeSoapClientAdapter.sendEmployeeToSoap(employee);
        } catch (RuntimeException e) {
            verify(employeeSoapMapper, times(1)).toSoapDTO(employee);
            verify(employeeSoapEndpoint, times(1)).createEmployee(soapEmployeeDTO);
            assertEquals("SOAP endpoint error", e.getMessage());
        }
    }
}
