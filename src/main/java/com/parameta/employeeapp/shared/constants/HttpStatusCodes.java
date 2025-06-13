package com.parameta.employeeapp.shared.constants;

public class HttpStatusCodes {
    
    public static final int BAD_REQUEST = 400;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int UNPROCESSABLE_ENTITY = 422; // Added constant
    public static final int NOT_IMPLEMENTED = 501;

    private HttpStatusCodes() {
    }
}
