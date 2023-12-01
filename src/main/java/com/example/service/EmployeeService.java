package com.example.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.dto.EmployeeDto;
import com.example.dto.ErrorResponse;
import com.example.model.Employee;

public interface EmployeeService {
	public ErrorResponse registerEmployee(EmployeeDto request);

	public List<EmployeeDto> getAllEmployee();

	public List<EmployeeDto> getByemployeeId(long employeeId);

	public List<Employee> findPaginated(int pageNo, int pageSize);

	public ResponseEntity<Object>  getAllEmployees();
}
