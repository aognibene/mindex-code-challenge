package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class CompensationServiceImplTest {
	
	@InjectMocks
	private CompensationServiceImpl compensationServiceImpl;
	
	@Mock
	private EmployeeService employeeService;
	
	
	@Mock
	private CompensationRepository compensationRepository;
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	} 
	
	@Test
	public void testCreateCompensation_ValidEmployee() {
		String employeeId = "test";
		BigDecimal salary = new BigDecimal(150000);
		Date effectiveDate = new Date();
		Compensation comp = new Compensation();
		comp.setEffectiveDate(effectiveDate);
		comp.setEmployeeId(employeeId);
		comp.setSalary(salary);
		
		when(employeeService.read(Mockito.anyString())).thenReturn(new Employee());
		when(compensationRepository.insert(Mockito.any(Compensation.class))).thenReturn(comp);
		
		Compensation responseComp = compensationServiceImpl.create(comp);
		
		verify(employeeService).read(employeeId);
		verify(compensationRepository).insert(comp);
		assertEquals(comp, responseComp);
		
	}
	
	@Test
	public void testCreateCompensation_InvalidEmployee() {

		Compensation compensation = new Compensation();
        compensation.setEmployeeId("test");
        
        when(employeeService.read(Mockito.anyString())).thenReturn(null); // Mock an invalid employee

        
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            compensationServiceImpl.create(compensation);
        });

        assertEquals("Invalid employeeId test", thrown.getMessage());
        verify(employeeService).read("test"); // Verify that employeeService.read was called with the correct ID
        verifyNoInteractions(compensationRepository); // Verify that compensationRepository.insert was not called
	}
	
	@Test
	public void testReadCompensation_ValidCompensation() {
		
		String employeeId = "test";
		BigDecimal salary = new BigDecimal(150000);
		Date effectiveDate = new Date();
		Compensation comp = new Compensation();
		comp.setEffectiveDate(effectiveDate);
		comp.setEmployeeId(employeeId);
		comp.setSalary(salary);
		
		when(compensationRepository.findByEmployeeId(Mockito.anyString())).thenReturn(comp);
		
		Compensation responseComp = compensationServiceImpl.read(employeeId);
		
		verify(compensationRepository).findByEmployeeId(employeeId);
		assertEquals(comp, responseComp);
		
		
	}
	
	@Test
	public void testReadCompensation_NoCompFound() {
		
		String employeeId = "test";
        
        when(compensationRepository.findByEmployeeId(Mockito.anyString())).thenReturn(null); // Mock an invalid employee

        
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            compensationServiceImpl.read(employeeId);
        });

        assertEquals("No Compensation found for employeeId test", thrown.getMessage());		
	}
	
	
	

}
