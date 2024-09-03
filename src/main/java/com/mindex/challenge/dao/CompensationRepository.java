package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
    
	/**
	 * *NOTE*
	 * If storing entire Employee class for employee field you could use findByEmployeeEmployeeId
	 * 
	 * 
	 * @param employeeId
	 * @return
	 */
	Compensation findByEmployeeId(String employeeId);
}
