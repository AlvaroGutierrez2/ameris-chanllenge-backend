package com.ameris.challenge.controller;

import com.ameris.challenge.dto.EmployeeDto;
import com.ameris.challenge.exception.EmployeeNotFoundException;
import com.ameris.challenge.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() throws EmployeeNotFoundException {
        log.debug("Getting list of all employees");
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees();
        log.debug("Returning list of employees");
        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }

    @GetMapping(value = "/employee/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long employeeId) throws EmployeeNotFoundException {
        log.debug("Getting employees for id: {}", employeeId);
        EmployeeDto employee = employeeService.getEmployeeById(employeeId);
        log.debug("Returning employee information for id: {} {}", employeeId, employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
