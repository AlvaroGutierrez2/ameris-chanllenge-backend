package com.ameris.challenge.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDto implements Serializable {
    private Long id;
    private Integer idNumber;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private BigDecimal salary;
    private BigDecimal annualSalary;
}
