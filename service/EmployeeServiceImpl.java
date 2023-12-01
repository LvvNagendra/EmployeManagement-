package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.dto.EmployeeDto;
import com.example.dto.ErrorResponse;
import com.example.model.Employee;
import com.example.reposatory.EmployeeReposatory;
import com.example.util.ResponseCodes;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeReposatory reposatory;

	@Override
	public ErrorResponse registerEmployee(EmployeeDto request) {
		Employee model= null;
		ErrorResponse response= new ErrorResponse();
		
		
		model=	new Employee();
		model.setEmployeeName(request.getEmployeeName());
		model.setEmailId(request.getEmailId());
		model.setPhoneNumber(request.getPhoneNumber());
		model.setOrginizationName(request.getOrginizationName());
		try {
		reposatory.save(model);
		response.setCode(ResponseCodes.S200.getErrorCode());
		response.setMessage(ResponseCodes.S200.getMessage());
		} catch (Exception e) {
			response.setCode(ResponseCodes.ER105.getErrorCode());
			response.setMessage(ResponseCodes.ER105.getMessage());
			
		}
		return response;
	}

	@Override
	public List<EmployeeDto> getAllEmployee() {
		EmployeeDto request=null;
		 List<Employee> list=reposatory.findAll();
		 List<EmployeeDto> listAll=new ArrayList<EmployeeDto>();
		 for(Employee emp:list) {
			 request= new EmployeeDto();
			 request.setEmployeeId(emp.getEmployeeId());
			 request.setEmployeeName(emp.getEmployeeName());
			 request.setEmailId(emp.getEmailId());
			 request.setPhoneNumber(emp.getPhoneNumber());
			 request.setOrginizationName(emp.getOrginizationName());
			 listAll.add(request);
			 
		 }
		return listAll;
	}

}
