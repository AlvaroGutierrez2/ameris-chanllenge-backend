package com.ameris.challenge.controller;

import com.ameris.challenge.dto.EmployeeDto;
import com.ameris.challenge.service.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Find multiples employees successful")
    void testGetAllEmployees() throws Exception {
        List<EmployeeDto> employeeList = Arrays.asList(
                EmployeeDto.builder()
                        .id(1L)
                        .idNumber(123456)
                        .firstName("Juan")
                        .lastName("Valdez")
                        .phoneNumber("+57112362259")
                        .salary(new BigDecimal("1000.00"))
                        .annualSalary(new BigDecimal("120000.00"))
                        .build(),
                EmployeeDto.builder()
                        .id(2L)
                        .idNumber(7891011)
                        .firstName("Julian")
                        .lastName("Carmon")
                        .phoneNumber("+5722113355")
                        .salary(new BigDecimal("5000.00"))
                        .annualSalary(new BigDecimal("60000.00"))
                        .build()
        );

        Mockito.when(employeeService.getAllEmployees()).thenReturn(employeeList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$.[0].idNumber", Matchers.is(123456)))
                .andExpect(jsonPath("$.[0].firstName", Matchers.is("Juan")))
                .andExpect(jsonPath("$.[0].lastName", Matchers.is("Valdez")))
                .andExpect(jsonPath("$.[0].phoneNumber", Matchers.is("+57112362259")))
                .andExpect(jsonPath("$.[0].salary", Matchers.is(1000.0)))
                .andExpect(jsonPath("$.[0].annualSalary", Matchers.is(120000.0)))
                .andExpect(jsonPath("$.[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$.[1].idNumber", Matchers.is(7891011)))
                .andExpect(jsonPath("$.[1].firstName", Matchers.is("Julian")))
                .andExpect(jsonPath("$.[1].lastName", Matchers.is("Carmon")))
                .andExpect(jsonPath("$.[1].phoneNumber", Matchers.is("+5722113355")))
                .andExpect(jsonPath("$.[1].salary", Matchers.is(5000.0)))
                .andExpect(jsonPath("$.[1].annualSalary", Matchers.is(60000.0)));
    }

    @Test
    @DisplayName("Find single employee successful")
    void testGetEmployeeByIds() throws Exception {
        EmployeeDto employee = EmployeeDto.builder()
                .id(1L)
                .idNumber(123456)
                .firstName("Juan")
                .lastName("Valdez")
                .phoneNumber("+57112362259")
                .salary(new BigDecimal("1000.00"))
                .annualSalary(new BigDecimal("120000.00"))
                .build();

        Mockito.when(employeeService.getEmployeeById(Mockito.anyLong())).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.idNumber", Matchers.is(123456)))
                .andExpect(jsonPath("$.firstName", Matchers.is("Juan")))
                .andExpect(jsonPath("$.lastName", Matchers.is("Valdez")))
                .andExpect(jsonPath("$.phoneNumber", Matchers.is("+57112362259")))
                .andExpect(jsonPath("$.salary", Matchers.is(1000.0)))
                .andExpect(jsonPath("$.annualSalary", Matchers.is(120000.0)));
    }
}
