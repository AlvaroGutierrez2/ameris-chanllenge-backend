package com.ameris.challenge;

import com.ameris.challenge.controller.EmployeeController;
import com.ameris.challenge.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChallengeApplicationTests {

	@Autowired
	private EmployeeController employeeController;

	@Autowired
	private EmployeeService employeeService;


	@Test
	void contextLoads() {
		Assertions.assertNotNull(employeeController);
		Assertions.assertNotNull(employeeService);
	}
}
