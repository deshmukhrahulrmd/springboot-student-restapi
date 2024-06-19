package com.qsp.springboot_student_3.exception;

public class EmptyDatabase extends RuntimeException{
	private String message;

	public EmptyDatabase(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	
}
