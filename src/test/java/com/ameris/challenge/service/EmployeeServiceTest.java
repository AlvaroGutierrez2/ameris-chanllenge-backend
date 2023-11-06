package com.ameris.challenge.service;


import com.ameris.challenge.dto.EmployeeDto;
import com.ameris.challenge.exception.EmployeeNotFoundException;
import com.ameris.challenge.model.Employee;
import com.ameris.challenge.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    @DisplayName("Multiples employees successful")
    void testGetAllEmployees() throws EmployeeNotFoundException {
        // arranges
        Mockito.when(employeeRepository.findAll()).thenReturn(getEmployeeList());

        // acts
        List<EmployeeDto> employees = employeeService.getAllEmployees();

        // asserts
        Assertions.assertTrue(employees.stream().count() == 2L, () -> "The size of list's employees should be 2");

        Assertions.assertEquals(1L, employees.get(0).getId());
        Assertions.assertEquals(123456, employees.get(0).getIdNumber());
        Assertions.assertEquals("Juan", employees.get(0).getFirstName());
        Assertions.assertEquals("Valdez", employees.get(0).getLastName());
        Assertions.assertEquals("+57112362259", employees.get(0).getPhoneNumber());
        Assertions.assertEquals(new BigDecimal("1500.00"), employees.get(0).getSalary());
        Assertions.assertEquals(new BigDecimal("18000.00"), employees.get(0).getAnnualSalary());

        Assertions.assertEquals(2L, employees.get(1).getId());
        Assertions.assertEquals(7891011, employees.get(1).getIdNumber());
        Assertions.assertEquals("Julian", employees.get(1).getFirstName());
        Assertions.assertEquals("Carmon", employees.get(1).getLastName());
        Assertions.assertEquals("+5722113355", employees.get(1).getPhoneNumber());
        Assertions.assertEquals(new BigDecimal("4850.00"), employees.get(1).getSalary());
        Assertions.assertEquals(new BigDecimal("58200.00"), employees.get(1).getAnnualSalary());
    }

    @Test
    @DisplayName("Check exception for multiples employees")
    void testGetAllEmployeesWithException() {
        // arranges
        Mockito.when(employeeRepository.findAll()).thenReturn(Collections.emptyList());

        // acts
        Exception exception = Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.getAllEmployees();
        });

        // asserts
        Assertions.assertEquals("There is no employee storage", exception.getMessage());
    }

    @Test
    @DisplayName("Find single employee successful")
    void testGetEmployeeById() throws EmployeeNotFoundException {
        // arranges
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(getEmployee());

        // acts
        EmployeeDto employee = employeeService.getEmployeeById(1L);

        // asserts
        Assertions.assertEquals(1L, employee.getId());
        Assertions.assertEquals(1122334455, employee.getIdNumber());
        Assertions.assertEquals("Maria", employee.getFirstName());
        Assertions.assertEquals("Valdez", employee.getLastName());
        Assertions.assertEquals("+1452452785", employee.getPhoneNumber());
        Assertions.assertEquals(new BigDecimal("8520.00"), employee.getSalary());
        Assertions.assertEquals(new BigDecimal("102240.00"), employee.getAnnualSalary());
    }

    @Test
    @DisplayName("Check exception for single employee")
    void testGetEmployeeByIdThrowException() {
        // arranges
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        // acts
        Exception exception = Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.getEmployeeById(123554L);
        });

        // asserts
        Assertions.assertEquals("The employee with id: 123554 does not exist", exception.getMessage());
    }


    private Optional<Employee> getEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setIdNumber(1122334455);
        employee.setFirstName("Maria");
        employee.setLastName("Valdez");
        employee.setPhoneNumber("+1452452785");
        employee.setSalary(new BigDecimal("8520.00"));
        return Optional.of(employee);
    }

    private List<Employee> getEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee;

        employee = new Employee();
        employee.setId(1L);
        employee.setIdNumber(123456);
        employee.setFirstName("Juan");
        employee.setLastName("Valdez");
        employee.setPhoneNumber("+57112362259");
        employee.setSalary(new BigDecimal("1500.00"));
        employeeList.add(employee);

        employee = new Employee();
        employee.setId(2L);
        employee.setIdNumber(7891011);
        employee.setFirstName("Julian");
        employee.setLastName("Carmon");
        employee.setPhoneNumber("+5722113355");
        employee.setSalary(new BigDecimal("4850.00"));
        employeeList.add(employee);

        return employeeList;
    }
}

