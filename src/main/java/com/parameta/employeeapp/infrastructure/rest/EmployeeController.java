package com.parameta.employeeapp.infrastructure.rest;

import com.parameta.employeeapp.application.services.EmployeeService;
import com.parameta.employeeapp.application.services.EmployeeServiceImpl;
import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeRequestDTOImpl;
import com.parameta.employeeapp.infrastructure.rest.dto.EmployeeResponseDTOImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService<EmployeeRequestDTOImpl, EmployeeResponseDTOImpl> employeeService;

    public EmployeeController(EmployeeService<EmployeeRequestDTOImpl, EmployeeResponseDTOImpl> employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Create a new employee", description = "Creates an employee with the provided details")
    @GetMapping
    public ResponseEntity<EmployeeResponseDTOImpl> createEmployee(@Valid @RequestParam String firstName,
                                                                  @RequestParam String lastName,
                                                                  @RequestParam String documentType,
                                                                  @RequestParam String documentNumber,
                                                                  @RequestParam String dateOfBirth,
                                                                  @RequestParam String hireDate,
                                                                  @RequestParam String position,
                                                                  @RequestParam Double salary) {
        EmployeeRequestDTOImpl requestDTO = new EmployeeRequestDTOImpl(
                firstName,
                lastName,
                documentType,
                documentNumber,
                LocalDate.parse(dateOfBirth),
                LocalDate.parse(hireDate),
                position,
                salary
        );

        EmployeeResponseDTOImpl response = employeeService.createEmployee(requestDTO);
        return ResponseEntity.ok(response);
    }
}
