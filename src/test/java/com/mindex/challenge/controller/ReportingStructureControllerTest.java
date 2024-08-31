package com.mindex.challenge.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;

@RunWith(SpringRunner.class)
@WebMvcTest(ReportingStructureController.class)
public class ReportingStructureControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private ReportingStructureService reportingStructureService;
	
	
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testReadReportingStructure() throws Exception {
		ReportingStructure reportingStructure = new ReportingStructure("test", 4);
		
		when(reportingStructureService.read(Mockito.anyString())).thenReturn(reportingStructure);
		
		mockMvc.perform(get("/reportingStructure/test"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.employeeId", is("test")))
        .andExpect(jsonPath("$.numberOfReports", is(4)));
	}
	
}	
