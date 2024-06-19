package com.qsp.springboot_student_3.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qsp.springboot_student_3.dto.Student;
import com.qsp.springboot_student_3.repository.StudentRepository;

@Repository
public class StudentDao {

	@Autowired
	private StudentRepository repository;
	
	//**********************************************To save student
	public Student saveStudent(Student student) {
		return repository.save(student);
	}

	//**********************************************To get student by id
	public Student getStudentById(int id) {
		Optional<Student> optional = repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
//		return repository.findById(id).get();
	}

	//**********************************************To get all students
	public List<Student> getAllStudents() {
		return repository.findAll();
	}

	//**********************************************To delete student
	public Student deleteStudentById(Student student) {
//		Optional<Student> optional = repository.findById(id);
//		if (optional.isPresent()) {
////			repository.deleteById(id);
////			return optional.get();
//			Student dbStudent = optional.get();
//			repository.delete(dbStudent);
//			return dbStudent;
//		}
//		return null;
		repository.delete(student);
		return student;
	}

	//**********************************************To update all the details of student
//	public Student fullDetailUpdateStudent(int id, Student student) {
//		Optional<Student> optional = repository.findById(id);
//		if (optional.isPresent()) {
//			student.setId(id);
//			return repository.save(student);
//		}
//		return null;
//	}

	//**********************************************To find student scored above 300
//	public List<Student> passedStudentList() {
//		List<Student> listOfAllStudents = repository.findAll();
//		
//		List<Student> listofPassedStudents = new ArrayList<Student>();
//		for (Student student : listOfAllStudents) {
//			if (student.getSecuredMarks()>300) {
//				listofPassedStudents.add(student);
//			}
//		}
//		return listofPassedStudents;
//	}

	//**********************************************To find student scored above given limit
//	public List<Student> passedStudentList(int securedMarks) {
//		List<Student> listOfAllStudents = repository.findAll();
//		
//		List<Student> listofPassedStudents = new ArrayList<Student>();
//		for (Student student : listOfAllStudents) {
//			if (student.getSecuredMarks()>=securedMarks) {
//				listofPassedStudents.add(student);
//			}
//		}
//		return listofPassedStudents;
//	}
	
	
	//**********************************************To save all students  at a time
	public List<Student> saveAllStudents(List<Student> stdList) {
		return repository.saveAll(stdList);
	}

	// Start $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Updation
	//**********************************************To update phone of student
//	public Student updatePhoneOfStudent(int id, long newPhone) {
//		Optional<Student> optional=repository.findById(id);
//		if (optional.isPresent()) {
//			Student dbStudent = optional.get();
//			dbStudent.setPhone(newPhone);
//			return repository.save(dbStudent);
//		}
//		return null;
//	}
	
	//**********************************************To update email of student
//	public Student updateEmailOfStudent(int id, String newEmail) {
//		Optional<Student> optional=repository.findById(id);
//		if (optional.isPresent()) {
//			Student dbStudent = optional.get();
//			dbStudent.setEmail(newEmail);
//			return repository.save(dbStudent);
//		}
//		return null;
//	}
//	
	//**********************************************To update name of student
//	public Student updateNameOfStudent(int id, String newName) {
//		Optional<Student> optional=repository.findById(id);
//		if (optional.isPresent()) {
//			Student dbStudent = optional.get();
//			dbStudent.setName(newName);
//			return repository.save(dbStudent);
//		}
//		return null;
//	}
	
	//**********************************************To update address of student
//	public Student updateAddressOfStudent(int id, String newAddress) {
//		Optional<Student> optional=repository.findById(id);
//		if (optional.isPresent()) {
//			Student dbStudent = optional.get();
//			dbStudent.setAddress(newAddress);
//			return repository.save(dbStudent);
//		}
//		return null;
//	}
	
	//**********************************************To update securedMarks of student
//	public Student updateSecuredMarksOfStudent(int id, int newSecuredMarks) {
//		Optional<Student> optional=repository.findById(id);
//		if (optional.isPresent()) {
//			Student dbStudent = optional.get();
//			if (newSecuredMarks<=dbStudent.getTotalMarks()) {
//				dbStudent.setSecuredMarks(newSecuredMarks);
//				return repository.save(dbStudent);
//			}
//			dbStudent.setSecuredMarks(0);
//			return repository.save(dbStudent);
//		}
//		return null;
//	}
	// End $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Updation

	//**********************************************To find student by phone number
	public Student findStudentByPhone(long phone) {
		return repository.findStudentByPhone(phone);
	}

	//**********************************************To find student by email id
	public Student getStudentByEmail(String email) {
		return repository.getStudentByEmail(email);
	}

	//**********************************************To find all students by name
	public List<Student> findAllStudentByName(String name) {
		return repository.findAllStudentByName(name);
	}
	
	//**********************************************To find all students by address
	public List<Student> getAllStudentByaddress(String address){
		return repository.getAllStudentByAddress(address);
	}

	//**********************************************To find all students between given range of marks
	public List<Student> studentsBetweenMarks(int mark1, int mark2) {
		return repository.studentsBetweenMarks(mark1,mark2);
	}

	//**********************************************To find all students grader than given grade (A - std grader than B,C,D (By ASCI value) )
	public List<Student> findStudentsByGradeGreaterThan(String grade) {
		return repository.findStudentsByGradeGreaterThan(grade);
	}
	
	//**********************************************To find all students less than given grade (C - std less than A,B (By ASCI value) )
	public List<Student> findStudentsByGradeLessThan(String grade) {
		return repository.findStudentsByGradeLessThan(grade);
	}
	
	//**********************************************To find all students grater than given percentage
	public List<Student> findStudentsByPercentageGreaterThan(double percentage) {
		return repository.findStudentsByPercentageGreaterThan(percentage);
	}

	public List<Student> findStudentsByPercentageLessThan(double percentage) {
		return repository.findStudentsByPercentageLessThan(percentage);
	}
	
	//**********************************************To find all students grater than given percentage
	
}
