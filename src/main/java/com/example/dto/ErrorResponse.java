package com.example.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ErrorResponse {
	private String code;
	private String message;

}
