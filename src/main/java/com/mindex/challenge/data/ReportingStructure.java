package com.mindex.challenge.data;

public class ReportingStructure {
	
	/**
	 * 
	 * *NOTE* 
	 * 
	 * Would like to point out multiple interpretations of employee from the directions.
	 * This could be the entire employee object or just a reference to the employee with the employeeId field.
	 * I chose the latter. Would need to know applications use cases to determine if entire employee object is needed 
	 * in the response. As for naming i chose employeeId instead of employee as stated in directions,
	 * but would need to know the client has any specific field names that are required before development. 
	 * 
	 * 
	 */
	private String employeeId;
	private int numberOfReports;
	
	public ReportingStructure(String employeeId, int numberOfReports) {
		this.employeeId = employeeId;
		this.numberOfReports = numberOfReports;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public int getNumberOfReports() {
		return numberOfReports;
	}

	public void setNumberOfReports(int numberOfReports) {
		this.numberOfReports = numberOfReports;
	}
	
	@Override
    public String toString() {
        return "ReportingStructure{" +
               "employeeId=" + employeeId +
               ", numberOfReports=" + numberOfReports +
               '}';
    }

}
