package com.qsp.springboot_student_3.exception;

public class NoNegativeElement extends RuntimeException{
	private String message;

	public NoNegativeElement(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
