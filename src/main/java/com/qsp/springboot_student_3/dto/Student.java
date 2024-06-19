package com.qsp.springboot_student_3.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(unique = true)
	private long phone;
	private String address;
	@Column(unique = true)
	private String email;
	private int securedMarks;
	private double totalMarks;
	private double percentage;
	private String grade;
}
