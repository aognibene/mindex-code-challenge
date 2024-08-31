package com.mindex.challenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.controller.ReportingStructureController;
import com.mindex.challenge.data.ReportingStructure;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportingStructureIntegrationTest {
	
	@Autowired
	ReportingStructureController reportingStructureController;
	
	
	@Test
	public void testReportingStructure() {
		String employeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
		ReportingStructure reportingStructure = reportingStructureController.read(employeeId);
		
		assertEquals(employeeId, reportingStructure.getEmployeeId());
		assertEquals(4, reportingStructure.getNumberOfReports());
		
	}
	
	
}
