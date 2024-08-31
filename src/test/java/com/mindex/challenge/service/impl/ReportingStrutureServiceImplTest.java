package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class ReportingStrutureServiceImplTest {
	
	@InjectMocks
	private ReportingStructureServiceImpl reportingStructureService;
	
	@Mock
	private EmployeeService employeeService;
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	} 
	
	@Test
    public void testReadValidEmployee() {
        
        Employee employee1 = new Employee();
        employee1.setEmployeeId("test");
        Employee directReport1 = new Employee();
        directReport1.setEmployeeId("testDirect1");
        
        
        Employee employee2 = new Employee();
        employee2.setEmployeeId("testDirect2");
        
        Employee directReport2 = employee2;
        employee1.setDirectReports(Arrays.asList(directReport1, directReport2));
        
        Employee indirectReport = new Employee();
        indirectReport.setEmployeeId("testIndirect");
        employee2.setDirectReports(Arrays.asList(indirectReport));
        
        
        when(employeeService.read("test")).thenReturn(employee1);
        when(employeeService.read("testDirect1")).thenReturn(new Employee());
        when(employeeService.read("testDirect2")).thenReturn(employee2);
        when(employeeService.read("testIndirect")).thenReturn(new Employee());

 
        ReportingStructure result = reportingStructureService.read("test");

        assertEquals("test", result.getEmployeeId());
        assertEquals(3, result.getNumberOfReports()); // 2 direct reports + 1 indirect (each direct report has no direct reports)
    }
	
	@Test
    public void testReadInvalidEmployee() {
        // Arrange
        when(employeeService.read("test")).thenReturn(null);

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            reportingStructureService.read("test");
        });

        assertEquals("Invalid employeeId test", thrown.getMessage());
    }

}
