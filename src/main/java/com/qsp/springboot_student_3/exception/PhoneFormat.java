package com.qsp.springboot_student_3.exception;

public class PhoneFormat extends RuntimeException{
	private String message;

	public PhoneFormat(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
