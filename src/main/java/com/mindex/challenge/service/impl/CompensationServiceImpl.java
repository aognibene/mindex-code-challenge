package com.mindex.challenge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CompensationServiceImpl implements CompensationService{
	
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	CompensationRepository compensationRepository;

	@Override
	public Compensation create(Compensation compensation) {
		LOG.debug("Creating compensation [{}]", compensation);
		
		if (employeeService.read(compensation.getEmployeeId()) == null) {
			throw new RuntimeException("Invalid employeeId " + compensation.getEmployeeId());
		}
		compensationRepository.insert(compensation);
		return compensation;
	}

	@Override
	public Compensation read(String employeeId) {
		LOG.debug("Reading compensation for employee id [{}]", employeeId);
		Compensation compensation = compensationRepository.findByEmployeeId(employeeId);
		
		if (compensation == null) {
			throw new RuntimeException("No Compensation found for employeeId " + employeeId);
		}
		
		return compensation;
	}
	
	
	
	

}
