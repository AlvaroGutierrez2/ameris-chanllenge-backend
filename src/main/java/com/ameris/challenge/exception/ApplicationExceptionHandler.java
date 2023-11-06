package com.ameris.challenge.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@Slf4j
@ResponseBody
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = EmployeeNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApplicationErrors handlerNotFoundException(Exception ex) {
        ApplicationErrors applicationErrors = new ApplicationErrors(ex.getMessage(), HttpStatus.NOT_FOUND.toString());
        applicationErrors.setDate(new Date());

        log.debug(ex.getMessage());
        return applicationErrors;
    }
}
