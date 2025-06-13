package com.parameta.employeeapp.application.services;

import com.parameta.employeeapp.application.mappers.EmployeeMapper;
import com.parameta.employeeapp.application.ports.out.EmployeeSoapClientPort;
import com.parameta.employeeapp.application.validators.EmployeeValidator;
import com.parameta.employeeapp.domain.model.Employee;
import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeRequestDTOImpl;
import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeResponseDTOImpl;
import com.parameta.employeeapp.shared.utils.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeSoapClientPort<Employee> employeeSoapClientPort;

    @Mock
    private EmployeeMapper<EmployeeRequestDTOImpl, EmployeeResponseDTOImpl> employeeMapper;

    @Mock
    private EmployeeValidator<EmployeeRequestDTOImpl> employeeValidator;
    
    
    private EmployeeService<EmployeeRequestDTOImpl, EmployeeResponseDTOImpl> employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeServiceImpl(employeeSoapClientPort, employeeMapper, employeeValidator);
    }
    @Test
    void testSendEmployeeToSoapFailure() {
        EmployeeRequestDTOImpl requestDTO = new EmployeeRequestDTOImpl(
                "John", "Doe", "ID", "12345",
                LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1),
                "Developer", 5000.0
        );

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

        doThrow(new RuntimeException("SOAP client error")).when(employeeSoapClientPort).sendEmployeeToSoap(employee);
        when(employeeMapper.toEmployee(requestDTO)).thenReturn(employee);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> employeeService.createEmployee(requestDTO));
        assertEquals("SOAP client error", exception.getMessage());

        verify(employeeValidator).validate(requestDTO);
        verify(employeeSoapClientPort).sendEmployeeToSoap(employee);
    }

    @Test
    void testInvalidMapping() {
        EmployeeRequestDTOImpl requestDTO = new EmployeeRequestDTOImpl(
                "John", "Doe", "ID", "12345",
                LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1),
                "Developer", 5000.0
        );

        when(employeeMapper.toEmployee(requestDTO)).thenThrow(new IllegalArgumentException("Mapping error"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> employeeService.createEmployee(requestDTO));
        assertEquals("Mapping error", exception.getMessage());

        verify(employeeValidator).validate(requestDTO);
        verify(employeeMapper).toEmployee(requestDTO);
        verifyNoInteractions(employeeSoapClientPort);
    }

    @Test
    void testInvalidDates() {
        EmployeeRequestDTOImpl requestDTO = new EmployeeRequestDTOImpl(
                "John", "Doe", "ID", "12345",
                LocalDate.of(2090, 1, 1), LocalDate.of(2020, 1, 1),
                "Developer", 5000.0
        );

        doThrow(new IllegalArgumentException("Invalid date")).when(employeeValidator).validate(requestDTO);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> employeeService.createEmployee(requestDTO));
        assertEquals("Invalid date", exception.getMessage());

        verify(employeeValidator).validate(requestDTO);
        verifyNoInteractions(employeeMapper);
        verifyNoInteractions(employeeSoapClientPort);
    }

    @Test
    void testExtremeValues() {
        EmployeeRequestDTOImpl requestDTO = new EmployeeRequestDTOImpl(
                "John", "Doe", "ID", "12345",
                LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1),
                "Developer", -5000.0
        );

        doThrow(new IllegalArgumentException("Invalid salary")).when(employeeValidator).validate(requestDTO);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> employeeService.createEmployee(requestDTO));
        assertEquals("Invalid salary", exception.getMessage());

        verify(employeeValidator).validate(requestDTO);
        verifyNoInteractions(employeeMapper);
        verifyNoInteractions(employeeSoapClientPort);
    }

    @Test
    void testIncompleteData() {
        EmployeeRequestDTOImpl requestDTO = new EmployeeRequestDTOImpl(
                null, "Doe", "ID", "12345",
                LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1),
                "Developer", 5000.0
        );

        doThrow(new IllegalArgumentException("Incomplete data")).when(employeeValidator).validate(requestDTO);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> employeeService.createEmployee(requestDTO));
        assertEquals("Incomplete data", exception.getMessage());

        verify(employeeValidator).validate(requestDTO);
        verifyNoInteractions(employeeMapper);
        verifyNoInteractions(employeeSoapClientPort);
    }

    @Test
    void testSuccessfulEmployeeCreation() {
        EmployeeRequestDTOImpl requestDTO = new EmployeeRequestDTOImpl(
                "John", "Doe", "ID", "12345",
                LocalDate.of(1988, 1, 1), LocalDate.of(2020, 1, 1),
                "Developer", 5000.0
        );

        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .documentType("ID")
                .documentNumber("12345")
                .dateOfBirth(LocalDate.of(1988, 1, 1))
                .hireDate(LocalDate.of(2020, 1, 1))
                .position("Developer")
                .salary(5000.0)
                .build();

        String expectedAge = DateUtils.formatPeriod(DateUtils.calculatePeriod(employee.getDateOfBirth(), LocalDate.now()));
        String expectedTimeInCompany = DateUtils.formatPeriod(DateUtils.calculatePeriod(employee.getHireDate(), LocalDate.now()));

        EmployeeResponseDTOImpl responseDTO = new EmployeeResponseDTOImpl(
                "John", "Doe", "ID", "12345",
                "1988-01-01", "2020-01-01", "Developer", 5000.0,
                expectedAge, expectedTimeInCompany
        );

        when(employeeMapper.toEmployee(requestDTO)).thenReturn(employee);
        when(employeeMapper.toResponseDTO(eq(employee), eq(expectedAge), eq(expectedTimeInCompany))).thenReturn(responseDTO);

        EmployeeResponseDTOImpl result = employeeService.createEmployee(requestDTO);

        assertEquals(responseDTO, result);
        verify(employeeValidator).validate(requestDTO);
        verify(employeeMapper).toEmployee(requestDTO);
        verify(employeeSoapClientPort).sendEmployeeToSoap(employee);
        verify(employeeMapper).toResponseDTO(eq(employee), eq(expectedAge), eq(expectedTimeInCompany));
    }
}
