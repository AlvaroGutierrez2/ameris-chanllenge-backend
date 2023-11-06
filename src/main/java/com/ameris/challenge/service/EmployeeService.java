package com.ameris.challenge.service;

import com.ameris.challenge.dto.EmployeeDto;
import com.ameris.challenge.exception.EmployeeNotFoundException;
import com.ameris.challenge.model.Employee;
import com.ameris.challenge.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * This function returns all employees storage in the BD and mapped to Dto
     *
     * @return List<EmployeeDto> contains information of the employee
     * @throws EmployeeNotFoundException
     */
    public List<EmployeeDto> getAllEmployees() throws EmployeeNotFoundException {
        log.debug("Looking for all employees in BD");
        List<Employee> employeeList = employeeRepository.findAll();

        if (CollectionUtils.isEmpty(employeeList)) {
            throw new EmployeeNotFoundException("There is no employee storage");
        }

        log.debug("Processing employee list");
        return employeeList.stream().map(employee ->
                        EmployeeDto.builder()
                                .id(employee.getId())
                                .idNumber(employee.getIdNumber())
                                .lastName(employee.getLastName())
                                .firstName(employee.getFirstName())
                                .phoneNumber(employee.getPhoneNumber())
                                .salary(employee.getSalary())
                                .annualSalary(calculateAnnualSalary(employee.getSalary()))
                                .build())
                .collect(Collectors.toList());
    }

    /**
     * This function finds the employee information using the id
     *
     * @param employeeId employee id
     * @return EmployeeDto contains the information of the employee
     * @throws EmployeeNotFoundException
     */
    public EmployeeDto getEmployeeById(Long employeeId) throws EmployeeNotFoundException {
        log.debug("Looking for employee with id: {}", employeeId);
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        optionalEmployee.orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        log.debug("Processing employee information for id: {}", employeeId);
        return optionalEmployee.map(employee -> EmployeeDto.builder()
                        .id(employee.getId())
                        .idNumber(employee.getIdNumber())
                        .lastName(employee.getLastName())
                        .firstName(employee.getFirstName())
                        .phoneNumber(employee.getPhoneNumber())
                        .salary(employee.getSalary())
                        .annualSalary(calculateAnnualSalary(employee.getSalary()))
                        .build())
                .get();
    }

    /**
     * This function calculates the annual salary of an employee
     * based on salary per month
     *
     * @param salary salary per month
     * @return BigDecimal value of the annual salary
     */
    private BigDecimal calculateAnnualSalary(BigDecimal salary) {
        if (Objects.nonNull(salary)) {
            return salary.multiply(new BigDecimal("12")).setScale(2, RoundingMode.HALF_EVEN);
        }

        return BigDecimal.ZERO;
    }
}
