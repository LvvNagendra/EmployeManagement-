package com.example.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonValidation {

	public boolean validateStringValues(String reqParamValue) {
		boolean validateResult = false;
		if (reqParamValue == null || reqParamValue.trim().replace(" ", "").isEmpty()
				|| reqParamValue.equalsIgnoreCase("null") || !reqParamValue.matches("[a-zA-Z]+"))
			validateResult = true;
		return validateResult;
	}
	public boolean validateNumber(String num) {
		boolean validateResult = false;

		if (num != null && num.trim() != null && !num.trim().replace(" ", "").isEmpty()) {
			String regex = "[0-9+]{6,15}";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(num);
			if (matcher.matches()) {
				validateResult = true;
			}
		}
		return validateResult;
	}
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
			.compile("^[^0-9][A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		
		public boolean validateEmail(String emailStr) {
			if (emailStr != null && emailStr.trim() != null && !emailStr.trim().replace(" ", "").isEmpty()) {
				if (emailStr != null) {
					Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
					return matcher.find();
				}
			}
			return false;
		}

}
