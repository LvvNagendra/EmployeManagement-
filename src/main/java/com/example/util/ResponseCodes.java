package com.example.util;

public enum ResponseCodes {

	S200("S200", "Employee Details Added Successfully"),
	
	S203("S203", "Report generated Successfully"),


	ER102("ER102", "EmployeeName shouldn't be null or blank or digit will be not accepted"),
	ER103("ER103", "EmployeeOrginizationName shouldn't be null or blank or digit will be not accepted"),
	ER104("ER104", "invalid EmailAddress"),
	ER105("ER105", "invalid phone Number");
	



	private String errorCode;
	private String message;

	private ResponseCodes(String errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
