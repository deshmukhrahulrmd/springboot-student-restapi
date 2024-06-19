package com.qsp.springboot_student_3.exception;

public class WrongComparison extends RuntimeException{
	private String message;

	public WrongComparison(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
