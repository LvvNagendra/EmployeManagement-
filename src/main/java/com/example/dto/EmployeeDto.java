package com.example.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmployeeDto {
	private long employeeId;
	private String employeeName;
	private String emailId;
	private String phoneNumber;
	private String orginizationName;
	private String password;

}
