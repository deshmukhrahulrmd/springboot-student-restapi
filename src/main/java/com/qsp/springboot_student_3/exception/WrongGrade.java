package com.qsp.springboot_student_3.exception;

public class WrongGrade extends RuntimeException{
	private String message;

	public WrongGrade(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
