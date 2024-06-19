package com.qsp.springboot_student_3.exception;

public class WrongMarksInput extends RuntimeException{
	private String message;

	public WrongMarksInput(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
