package com.qsp.springboot_student_3.exception;

public class ShortLength extends RuntimeException{
	
	private String message;

	public ShortLength(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
