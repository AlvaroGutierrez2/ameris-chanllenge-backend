package com.ameris.challenge.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class EmployeeDtoTest {

    @Test
    @DisplayName("Testing employee builder")
    void testEmployeeBuilder() {
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(1L)
                .idNumber(123456)
                .firstName("Juan")
                .lastName("Valdez")
                .phoneNumber("+57112362259")
                .salary(new BigDecimal("1000.00"))
                .annualSalary(new BigDecimal("120000.00"))
                .build();

        Assertions.assertEquals(1L, employeeDto.getId());
        Assertions.assertEquals(123456, employeeDto.getIdNumber());
        Assertions.assertEquals("Juan", employeeDto.getFirstName());
        Assertions.assertEquals("Valdez", employeeDto.getLastName());
        Assertions.assertEquals("+57112362259", employeeDto.getPhoneNumber());
        Assertions.assertEquals(new BigDecimal("1000.00"), employeeDto.getSalary());
        Assertions.assertEquals(new BigDecimal("120000.00"), employeeDto.getAnnualSalary());
    }
}

