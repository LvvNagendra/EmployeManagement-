package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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
		Employee model = null;
		ErrorResponse response = new ErrorResponse();

		model = new Employee();
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
		EmployeeDto request = null;
		List<Employee> list = reposatory.findAll();
		List<EmployeeDto> listAll = new ArrayList<EmployeeDto>();
		for (Employee emp : list) {
			request = new EmployeeDto();
			request.setEmployeeId(emp.getEmployeeId());
			request.setEmployeeName(emp.getEmployeeName());
			request.setEmailId(emp.getEmailId());
			request.setPhoneNumber(emp.getPhoneNumber());
			request.setOrginizationName(emp.getOrginizationName());
			listAll.add(request);

		}
		return listAll;
	}
	
	
	

	@Override
	public List<EmployeeDto> getByemployeeId(long employeeId) {
		EmployeeDto request = null;
		List<Employee> list = reposatory.getByEmployeeId(employeeId);
		List<EmployeeDto> listAll = new ArrayList<EmployeeDto>();
		for (Employee emp : list) {
			request = new EmployeeDto();
			request.setEmployeeId(emp.getEmployeeId());
			request.setEmployeeName(emp.getEmployeeName());
			request.setEmailId(emp.getEmailId());
			request.setPhoneNumber(emp.getPhoneNumber());
			request.setOrginizationName(emp.getOrginizationName());
			listAll.add(request);
		}
		return listAll;
	}

	@Override
	public List<Employee> findPaginated(int pageNo, int pageSize) {

		PageRequest paging = PageRequest.of(pageNo, pageSize);
		Page<Employee> pagedResult = reposatory.findAll(paging);
		return pagedResult.toList();

	}
	
	
	
	public ResponseEntity<Object> getAllEmployees(){
		
		
		
		
		
		
		List<String> employe=reposatory.findAll().stream().map(emp->emp.getEmployeeName()).collect(Collectors.toList());
		Map<String,Long> map =employe.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		return new ResponseEntity<>(map,HttpStatus.OK);
		
	}
//	@Override
//    public UserDetails loadUserByUsername(String employeeName) throws UsernameNotFoundException {
//		Employee user = reposatory.findByEmployeeName(employeeName);
//		 return new org.springframework.security.core.userdetails.User(user.getEmployeeName(), user.getPassword(), new ArrayList<>());
//    }
   

}
