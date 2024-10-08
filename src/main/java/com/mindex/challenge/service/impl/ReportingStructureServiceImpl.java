package com.mindex.challenge.service.impl;

import java.util.LinkedList;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);
	
	@Autowired
	private EmployeeService employeeService;

	@Override
	public ReportingStructure read(String employeeId) {
		LOG.debug("Reading resporting structure for employee id [{}]", employeeId);
		
		Employee employee = employeeService.read(employeeId);
		
		if (employee == null) {
			throw new RuntimeException("Invalid employeeId " + employeeId);
		}
		
		int totalReports = getTotalReports(employee);
		
		return new ReportingStructure(employeeId, totalReports);
	}

	/**
	 * Counts the reports for a given employee.
	 * 
	 * Performance: Can use iterative solution if recursion has performance issues with a lot of nested reports
	 * Please see iterative solution below.
	 * 
	 * Circular References: Assuming no one can be a report of someone that reports to them this wont have circular references.
	 * But if they were possible we would need to check that or else end up in infinite loop.
	 * 
	 * @param employee the employee for whom to count reports
     * @return the total number of reports
	 */
	private int getTotalReports(Employee employee) {
		int count = 0;
		if (employee.getDirectReports() != null) {
			for (Employee report : employee.getDirectReports()) {
				count += 1 + getTotalReports(employeeService.read(report.getEmployeeId()));
			}
		}
		return count;
	}
	
	/**
     * Iteratively count the reports using a breadth-first search approach.
     * 
     * @param employee the employee for whom to count reports
     * @return the total number of reports
     */
	private int getTotalReportsIteratively(Employee employee) {
		if (employee.getDirectReports() == null || employee.getDirectReports().size() <= 0){
			return 0;
		}
		
		int totalReports = 0;
		Queue<Employee> queue = new LinkedList<>();
		queue.add(employee);
		
		while (!queue.isEmpty()) {
			Employee current = queue.poll();
			if (current.getDirectReports() != null) {
				for (Employee directReport : current.getDirectReports()) {
					queue.add(employeeService.read(directReport.getEmployeeId()));
					totalReports++;
				}
			}
		}
		return totalReports;
	}

}
