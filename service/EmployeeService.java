package com.example.service;

import java.util.List;

import com.example.dto.EmployeeDto;
import com.example.dto.ErrorResponse;


public interface EmployeeService {
	public ErrorResponse registerEmployee(EmployeeDto request);
	
	public List<EmployeeDto> getAllEmployee();

}
