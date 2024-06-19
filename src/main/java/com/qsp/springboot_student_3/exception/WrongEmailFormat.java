package com.qsp.springboot_student_3.exception;

public class WrongEmailFormat extends RuntimeException{
	private String message;

	public WrongEmailFormat(String message) {
		super();
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	}
}
