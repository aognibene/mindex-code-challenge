package com.mindex.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

@RestController
public class CompensationController {
	
	@Autowired
	CompensationService compensationService;
	
	@PostMapping("/compensation")
	public Compensation create(@RequestBody Compensation compensation) {
		
		return compensationService.create(compensation);
	}
	
	@GetMapping("/compensation/{id}")
	public Compensation read(@PathVariable String id) {
		return compensationService.read(id);
	}
	
}
