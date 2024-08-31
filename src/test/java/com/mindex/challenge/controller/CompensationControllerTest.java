package com.mindex.challenge.controller;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

@RunWith(SpringRunner.class)
@WebMvcTest(CompensationController.class)
public class CompensationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompensationService compensationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCompensation() throws Exception {
        Compensation compensation = new Compensation();
        compensation.setEmployeeId("123");
        compensation.setSalary(new BigDecimal(150000));
        compensation.setEffectiveDate(new Date());

        when(compensationService.create(Mockito.any(Compensation.class))).thenReturn(compensation);

     
        mockMvc.perform(post("/compensation")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(compensation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId", is("123")))
                .andExpect(jsonPath("$.salary", is(150000)));
    }

    @Test
    public void testReadCompensation() throws Exception {
        Compensation compensation = new Compensation();
        compensation.setEmployeeId("123");
        compensation.setSalary(new BigDecimal(150000));
        compensation.setEffectiveDate(new Date());

        when(compensationService.read(Mockito.anyString())).thenReturn(compensation);

        mockMvc.perform(get("/compensation/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId", is("123")))
                .andExpect(jsonPath("$.salary", is(150000)));
    }
}
