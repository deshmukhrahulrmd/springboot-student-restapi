package com.qsp.springboot_student_3.exception;

public class IdNotFound extends RuntimeException{
	private String message;

	// Create constructor for setting data to message variable
	public IdNotFound(String message) {
		super();
		this.message = message;
	}
	
	// Override getMessage present in 'Throwable Class' to pass a message where exception occur
	@Override
	public String getMessage() {
		return message;
	}
}
