package com.mindex.challenge;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.controller.CompensationController;
import com.mindex.challenge.data.Compensation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompensationIntegrationTest {
	
	@Autowired
	CompensationController compensationController;
	
	@Test
	public void testCompensation() throws ParseException {
		
		String employeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
		BigDecimal salary = new BigDecimal(150000);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String dateString = "08-30-2024";
        Date date = dateFormat.parse(dateString);
		
		
		Compensation comp = new Compensation();
		comp.setEmployeeId(employeeId);
		comp.setEffectiveDate(date);
		comp.setSalary(salary);
		
		Compensation createdComp = compensationController.create(comp);
		
		Compensation readComp = compensationController.read(employeeId);
		
		assertEquals(employeeId, readComp.getEmployeeId());
		assertEquals(salary, readComp.getSalary());
		assertEquals(date, readComp.getEffectiveDate());
		
		
	}
	
}
