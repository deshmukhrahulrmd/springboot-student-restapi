package com.qsp.springboot_student_3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qsp.springboot_student_3.dto.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

//	@Query("SELECT std FROM Student std WHERE std.securedMarks<=totalMarks")
//	List<Student> validationOfMarks(List<Student> stdList);
	
	Student findStudentByPhone(long phone);

	Student getStudentByEmail(String email);

	List<Student> findAllStudentByName(String name);
	
	List<Student> getAllStudentByAddress(String address);

	@Query("SELECT std FROM Student std WHERE std.securedMarks BETWEEN ?1 AND ?2")
	List<Student> studentsBetweenMarks(int mark1, int mark2);

	List<Student> findStudentsByGradeGreaterThan(String grade);
	
	List<Student> findStudentsByGradeLessThan(String grade);
	
	List<Student> findStudentsByPercentageGreaterThan(double percentage);
	
	List<Student> findStudentsByPercentageLessThan(double percentage);
}
