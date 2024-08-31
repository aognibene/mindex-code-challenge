package com.mindex.challenge.data;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Compensation {
	
	
	/**
	 * *NOTE*
	 * 
	 * Want to point out multiple interpretations of the employee field in the directions
	 * 
	 * This could be just the reference to an employee using employeeId. Or the entire Employee class.
	 * I chose to go with employeeId because I did not see the need to store the employee object twice.
	 * 
	 */
	private String employeeId;
	private BigDecimal salary;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM-dd-yyyy")
	private Date effectiveDate;
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	
}
