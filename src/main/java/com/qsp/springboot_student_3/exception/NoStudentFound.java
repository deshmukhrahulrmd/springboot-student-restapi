package com.qsp.springboot_student_3.exception;

public class NoStudentFound extends RuntimeException{
	private String message;

	public NoStudentFound(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
