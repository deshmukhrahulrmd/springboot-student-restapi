package com.qsp.springboot_student_3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.springboot_student_3.dao.StudentDao;
import com.qsp.springboot_student_3.dto.Student;
import com.qsp.springboot_student_3.service.StudentService;
import com.qsp.springboot_student_3.util.ResponseStructure;

@RestController
public class UserController {
//	@Autowired
//	private StudentDao dao;
	
	@Autowired
	private StudentService service;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Student>> save(@RequestBody Student student) {
		return service.saveStudent(student);
	}
	
	@GetMapping("/find")
	public ResponseEntity<ResponseStructure<Student>> getById(@RequestParam int id){
		return service.getStudentById(id);
	}
	
	@GetMapping("/findall")
	public ResponseEntity<ResponseStructure<List<Student>>> getAll(){
		return service.getAllStudents();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<Student>> deleteById(@PathVariable int id) {
		return service.deleteStudentById(id);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Student>> fullDetailUpdate(@RequestParam int id, @RequestBody Student student) {
		return service.fullDetailUpdateStudent(id,student);
	}

	@PostMapping("/saveall")
	public ResponseEntity<ResponseStructure<List<Student>>> saveAll(@RequestBody List<Student> stdList){
		return service.saveAllStudents(stdList);
	}
	
	@PatchMapping("/updatephone")
	public ResponseEntity<ResponseStructure<Student>> updatePhone(@RequestParam int id, @RequestParam long newPhone) {
		return service.updatePhoneOfStudent(id,newPhone);
	}
	
	@PatchMapping("/updateemail")
	public ResponseEntity<ResponseStructure<Student>> updateEmail(@RequestParam int id, @RequestParam String newEmail) {
		return service.updateEmailOfStudent(id, newEmail);
	}
	
	@PatchMapping("/updatename")
	public ResponseEntity<ResponseStructure<Student>> updateName(@RequestParam int id, @RequestParam String newName) {
		return service.updateNameOfStudent(id, newName);
	}
	
	@PatchMapping("/updateaddress")
	public ResponseEntity<ResponseStructure<Student>> updateAddress(@RequestParam int id, @RequestParam String newAddress) {
		return service.updateAddressOfStudent(id, newAddress);
	}
	
	@PatchMapping("/updatemarks")
	public ResponseEntity<ResponseStructure<Student>> updateMarks(@RequestParam int id, @RequestParam int newSecuredMarks) {
		return service.updateSecuredMarksOfStudent(id, newSecuredMarks);
	}
	
	@GetMapping("/findbyphone")
	public ResponseEntity<ResponseStructure<Student>> findByPhone(@RequestParam long phone) {
		return service.findStudentByPhone(phone);
	}
	
	@GetMapping("/findbyemail/{email}")
	public ResponseEntity<ResponseStructure<Student>> getByEmail(@PathVariable String email) {
		return service.getStudentByEmail(email);
	}
	
	@GetMapping("/findallbyname/{name}")
	public ResponseEntity<ResponseStructure<List<Student>>> findAllByName(@PathVariable String name){
		return service.findAllStudentByName(name);
	}
	
	@GetMapping("/findallbyaddress/{address}")
	public ResponseEntity<ResponseStructure<List<Student>>> getAllByaddress(@PathVariable String address){
		return service.getAllStudentByaddress(address);
	}
//	
//	
//	@GetMapping("/passing")
//	public List<Student> passingList(){
//		return service.passedStudentList();
//	}
	
	@GetMapping("/passing/{limit}")
	public ResponseEntity<ResponseStructure<List<Student>>> passingList(@PathVariable int limit){
		return service.passedStudentList(limit);
	}
	
	@GetMapping("/marksbetween")
	public ResponseEntity<ResponseStructure<List<Student>>> studentsBetweenMarks(@RequestParam int mark1, @RequestParam int mark2){
		return service.studentsBetweenMarks(mark1,mark2);
	}
	
	@GetMapping("/belowgrade")
	public ResponseEntity<ResponseStructure<List<Student>>> studentsGreaterThanGrade(@RequestParam String grade){
		return service.studentsGreaterThanGrade(grade);
	}
	
	@GetMapping("/abovegrade")
	public ResponseEntity<ResponseStructure<List<Student>>> studentsLessThanGrade(@RequestParam String grade){
		return service.studentsLessThanGrade(grade);
	}
	
	@GetMapping("/abovepercentage")
	public ResponseEntity<ResponseStructure<List<Student>>> studentsGreaterThanPercentage(@RequestParam double percentage){
		return service.studentsGreaterThanPercentage(percentage);
	}
	
	@GetMapping("/lesspercentage")
	public ResponseEntity<ResponseStructure<List<Student>>> studentsLessThanPercentage(@RequestParam double percentage){
		return service.studentsLessThanPercentage(percentage);
	}
	
}
