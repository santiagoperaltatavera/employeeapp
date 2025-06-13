package com.parameta.employeeapp.infrastructure.soap.endpoint;

import com.parameta.employeeapp.infrastructure.soap.dto.SoapEmployeeDTO;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;


@WebService
public interface EmployeeSoapEndpoint {

    @WebMethod
    void createEmployee(@WebParam(name = "employee") SoapEmployeeDTO employee);
}
