package com.parameta.employeeapp.application.services;

public interface EmployeeService<RequestDTO, ResponseDTO> {
    ResponseDTO createEmployee(RequestDTO requestDTO);
}
