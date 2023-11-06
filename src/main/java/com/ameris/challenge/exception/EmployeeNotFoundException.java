package com.ameris.challenge.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends Exception {
    private static final String MESSAGE_CONTENT = "The employee with id: %s does not exist";
    private String message;

    public EmployeeNotFoundException(Long employeeId) {
        super();
        this.message = String.format(MESSAGE_CONTENT, employeeId);
    }

    public EmployeeNotFoundException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
