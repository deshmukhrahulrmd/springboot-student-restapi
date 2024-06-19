package com.qsp.springboot_student_3.exception;

public class WrongPrecentage extends RuntimeException{
	private String message;

	public WrongPrecentage(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
