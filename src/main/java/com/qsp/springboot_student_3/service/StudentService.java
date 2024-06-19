package com.qsp.springboot_student_3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.springboot_student_3.dao.StudentDao;
import com.qsp.springboot_student_3.dto.Student;
import com.qsp.springboot_student_3.exception.EmptyDatabase;
import com.qsp.springboot_student_3.exception.IdNotFound;
import com.qsp.springboot_student_3.exception.ShortLength;
import com.qsp.springboot_student_3.exception.WrongComparison;
import com.qsp.springboot_student_3.exception.NoNegativeElement;
import com.qsp.springboot_student_3.exception.NoStudentFound;
import com.qsp.springboot_student_3.exception.PhoneFormat;
import com.qsp.springboot_student_3.exception.WrongEmailFormat;
import com.qsp.springboot_student_3.exception.WrongGrade;
import com.qsp.springboot_student_3.exception.WrongMarksInput;
import com.qsp.springboot_student_3.exception.WrongPrecentage;
import com.qsp.springboot_student_3.util.ResponseStructure;

@Service
public class StudentService {

	@Autowired
	private StudentDao dao;
	
	//********************************************** Validating marks of a student before storing into DB
	public Student validationOfMarks(Student student) {
		if (student.getSecuredMarks()<=student.getTotalMarks()) {
			double persentage = (student.getSecuredMarks()/student.getTotalMarks())*100;
			student.setPercentage(persentage);
			return student;
		}
		return null;
	}
	
	//********************************************** Validating marks of students before storing into DB
	public List<Student> validationOfMarks(List<Student> stdList){
		List<Student> correctMarksList = new ArrayList<Student>();
		for (Student student : stdList) {
			if (student.getSecuredMarks()<=student.getTotalMarks()) {
				double persentage = (student.getSecuredMarks()/student.getTotalMarks())*100;
				student.setPercentage(persentage);
				correctMarksList.add(student);
			}
		}
		return correctMarksList;
	}

	public ResponseEntity<ResponseStructure<Student>> saveStudent(Student student) {
		Student validMStudent = validationOfMarks(student);
		if (validMStudent.getPercentage()<40) {
			validMStudent.setGrade("F");
		} else if(validMStudent.getPercentage()>=40 && validMStudent.getPercentage()<49){
			validMStudent.setGrade("E-");
		} else if(validMStudent.getPercentage()>=49 && validMStudent.getPercentage()<59) {
			validMStudent.setGrade("E");
		} else if(validMStudent.getPercentage()>=59 && validMStudent.getPercentage()<69) {
			validMStudent.setGrade("D");
		} else if(validMStudent.getPercentage()>=69 && validMStudent.getPercentage()<79) {
			validMStudent.setGrade("C");
		} else if(validMStudent.getPercentage()>=79 && validMStudent.getPercentage()<89) {
			validMStudent.setGrade("B");
		} else if(validMStudent.getPercentage()>89) {
			validMStudent.setGrade("A");
		}
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		structure.setMessage("Saved successful !!!");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveStudent(validMStudent));
//		return structure;
		return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Student>> getStudentById(int id) {
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		if (id>0) {
			Student dbStudent = dao.getStudentById(id);
			if (dbStudent!=null) {
				structure.setMessage("Student found !!!");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(dbStudent);
//				return structure;
				return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.FOUND);
			} else {
//				structure.setMessage("Student with Id-"+id+" not found !!!");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(dbStudent);
//				return structure;
				throw new IdNotFound("Id not found !!!");
			}
		} else {
			throw new NoNegativeElement("Negative Id not allowed !!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> getAllStudents() {
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
		List<Student> stdList = dao.getAllStudents();
		if (!stdList.isEmpty()) {
			structure.setMessage("Students found !!!");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(stdList);
//			return structure;
			return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.FOUND);
		} else {
//			structure.setMessage("Yet no sudent added in DataBase !!!");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(stdList);
//			return structure;
			throw new EmptyDatabase("No data present in Database !!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<Student>> deleteStudentById(int id) {
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		if (id>0) {
			Student dbStudent = dao.getStudentById(id);
			if (dbStudent!=null) {
				structure.setMessage("Stduent deleted !!!");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dao.deleteStudentById(dbStudent));
//				return structure;
				return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.OK);
			} else {
//				structure.setMessage("No stduent found with Id-"+id+" !!!");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(dbStudent);
//				return structure;
				throw new IdNotFound("Id not ofund !!!");
			}
		} else {
			throw new NoNegativeElement("Negative Id not allowed !!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<Student>> fullDetailUpdateStudent(int id, Student student) {
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		if (id>0) {
			Student dbStudent = dao.getStudentById(id);
			if (dbStudent!=null) {
				student.setId(id);
				Student validMStudent = validationOfMarks(student);
				if (validMStudent.getPercentage()<40) {
					validMStudent.setGrade("F");
				} else if(validMStudent.getPercentage()>=40 && validMStudent.getPercentage()<49){
					validMStudent.setGrade("E-");
				} else if(validMStudent.getPercentage()>=49 && validMStudent.getPercentage()<59) {
					validMStudent.setGrade("E");
				} else if(validMStudent.getPercentage()>=59 && validMStudent.getPercentage()<69) {
					validMStudent.setGrade("D");
				} else if(validMStudent.getPercentage()>=69 && validMStudent.getPercentage()<79) {
					validMStudent.setGrade("C");
				} else if(validMStudent.getPercentage()>=79 && validMStudent.getPercentage()<89) {
					validMStudent.setGrade("B");
				} else if(validMStudent.getPercentage()>89) {
					validMStudent.setGrade("A");
				}
				structure.setMessage("Student updated !!!");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dao.saveStudent(validMStudent));
//				return structure;
				return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.OK);
			} else {
//				structure.setMessage("No student found with Id-"+id+" !!!");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(dbStudent);
//				return structure;
				throw new IdNotFound("Id not found !!!");
			}
		} else {
			throw new NoNegativeElement("Negative Id not allowed !!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> saveAllStudents(List<Student> stdList) {
		List<Student> validMStudentList  = validationOfMarks(stdList);
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
		for (Student student : validMStudentList) {
			if (student.getPercentage()<40) {
				student.setGrade("F");
			} else if(student.getPercentage()>=40 && student.getPercentage()<49){
				student.setGrade("E-");
			} else if(student.getPercentage()>=49 && student.getPercentage()<59) {
				student.setGrade("E");
			} else if(student.getPercentage()>=59 && student.getPercentage()<69) {
				student.setGrade("D");
			} else if(student.getPercentage()>=69 && student.getPercentage()<79) {
				student.setGrade("C");
			} else if(student.getPercentage()>=79 && student.getPercentage()<89) {
				student.setGrade("B");
			} else if(student.getPercentage()>89) {
				student.setGrade("A");
			}
			
		}
		structure.setMessage("All Students saved !!!");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveAllStudents(validMStudentList));
//		return structure;
		return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> passedStudentList(int limit) {
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
		if (limit>=0) {
			List<Student> stdList = dao.getAllStudents();
			List<Student> passedStudentList = new ArrayList<Student>();
			// Checking students are present in DB or not 
			if (!stdList.isEmpty()) {
				for (Student student : stdList) {
					if (student.getSecuredMarks()>=limit) {
						passedStudentList.add(student);
					}
				}
			} else {
//				structure.setMessage("Yet no student added in DataBase !!!");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(stdList);
//				return structure;
				throw new EmptyDatabase("No data present in Database !!!");
			}
			
			// Checking passed student list is empty or not 
			if (!passedStudentList.isEmpty()) {
				structure.setMessage("Sudents found above Mark-"+limit+" !!!");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(passedStudentList);
//				return structure;
				return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.FOUND);
			} else {
//				structure.setMessage("No sudents found above Mark-"+limit+" !!!");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(passedStudentList);
//				return structure;
				throw new NoStudentFound("No student found with "+limit);
			}
		} else {
			throw new NoNegativeElement("Negative Limit not allowed !!!");
		}
	}

	public ResponseEntity<ResponseStructure<Student>> updatePhoneOfStudent(int id, long newPhone) {
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		if (id>0) {
			int temp=0;
			for (long i = newPhone; i!=0; i/=10) {
				long rem = i%10;
				temp++;
			}
			if (temp==10) {
				Student dbStudent = dao.getStudentById(id);
				if (dbStudent!=null) {
					dbStudent.setId(id);
					dbStudent.setPhone(newPhone);
					structure.setMessage("Phone updated !!!");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(dao.saveStudent(dbStudent));
//					return structure;
					return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.OK);
				} else {
//					structure.setMessage("No student found with Id-"+id+" !!!");
//					structure.setStatus(HttpStatus.NOT_FOUND.value());
//					structure.setData(dbStudent);
//					return structure;
					throw new IdNotFound("Id not found !!!");
				}
			} else {
//				structure.setMessage("Please check Phone Number !!!");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(null);
//				return structure;
				throw new PhoneFormat("Check phone number !!!");
			}
		} else {
			throw new NoNegativeElement("Negative Id not allowed !!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<Student>> updateEmailOfStudent(int id, String newEmail) {
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		if (id>0) {
			if (newEmail.endsWith("@gmail.com")) {
				Student dbStudent = dao.getStudentById(id);
				if (dbStudent!=null) {
					dbStudent.setId(id);
					dbStudent.setEmail(newEmail);
					structure.setMessage("Email updated !!!");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(dao.saveStudent(dbStudent));
//					return structure;
					return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.OK);
				} else {
//					structure.setMessage("No student found with Id-"+id+" !!!");
//					structure.setStatus(HttpStatus.NOT_FOUND.value());
//					structure.setData(dbStudent);
//					return structure;
					throw new IdNotFound("Id not found");
				}
			} else {
				throw new WrongEmailFormat("Enter valid email");
			}
		} else {
			throw new NoNegativeElement("Negative Id not allowed !!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<Student>> updateNameOfStudent(int id, String newName) {
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		if (id>0) {
			if (newName.length()>=2) {
				Student dbStudent = dao.getStudentById(id);
				if (dbStudent!=null) {
					dbStudent.setId(id);
					dbStudent.setName(newName);
					structure.setMessage("Name updated !!!");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(dao.saveStudent(dbStudent));
//					return structure;
					return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.OK);
				} else {
//					structure.setMessage("No student found with Id-"+id+" !!!");
//					structure.setStatus(HttpStatus.NOT_FOUND.value());
//					structure.setData(dbStudent);
//					return structure;
					throw new IdNotFound("Id not found !!!");
				}
			} else {
				throw new ShortLength("Enter a valid name !!!");
			}
		} else {
			throw new NoNegativeElement("Negative Id not allowed !!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<Student>> updateAddressOfStudent(int id, String newAddress) {
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		if (id>0) {
			if (newAddress.length()>=3) {
				Student dbStudent = dao.getStudentById(id);
				if (dbStudent!=null) {
					dbStudent.setId(id);
					dbStudent.setAddress(newAddress);
					structure.setMessage("Address updated !!!");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(dao.saveStudent(dbStudent));
//					return structure;
					return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.OK);
				} else {
//					structure.setMessage("No student found with Id-"+id+" !!!");
//					structure.setStatus(HttpStatus.NOT_FOUND.value());
//					structure.setData(dbStudent);
//					return structure;
					throw new IdNotFound("Id not found !!!");
				}
			} else {
				throw new ShortLength("Address is too short !!!");
			}
		} else {
			throw new NoNegativeElement("Negative Id not allowed !!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<Student>> updateSecuredMarksOfStudent(int id, int newSecuredMarks) {
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		if (id>0) {
			Student dbStudent = dao.getStudentById(id);
			if (dbStudent!=null) {
				dbStudent.setId(id);
				dbStudent.setSecuredMarks(newSecuredMarks);
				
				Student validMStudent = validationOfMarks(dbStudent);
				if (validMStudent!=null) {
					if (validMStudent.getPercentage()<40) {
						validMStudent.setGrade("F");
					} else if(validMStudent.getPercentage()>=40 && validMStudent.getPercentage()<49){
						validMStudent.setGrade("E-");
					} else if(validMStudent.getPercentage()>=49 && validMStudent.getPercentage()<59) {
						validMStudent.setGrade("E");
					} else if(validMStudent.getPercentage()>=59 && validMStudent.getPercentage()<69) {
						validMStudent.setGrade("D");
					} else if(validMStudent.getPercentage()>=69 && validMStudent.getPercentage()<79) {
						validMStudent.setGrade("C");
					} else if(validMStudent.getPercentage()>=79 && validMStudent.getPercentage()<89) {
						validMStudent.setGrade("B");
					} else if(validMStudent.getPercentage()>89) {
						validMStudent.setGrade("A");
					}
					
					structure.setMessage("Secured marks updated !!!");	
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(dao.saveStudent(dbStudent));
//					return structure;
					return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.OK);
				} else {
//					structure.setMessage("Not Updated, secured marks should be less than total marks !!!");
//					structure.setStatus(HttpStatus.NOT_FOUND.value());
//					structure.setData(dbStudent);
//					return structure;
					throw new WrongMarksInput("Wrong marks entered !!!");
				}
			} else {
//				structure.setMessage("No student found with Id-"+id+" !!!");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(dbStudent);
//				return structure;
				throw new IdNotFound("Id not found !!!");
			}
		} else {
			throw new NoNegativeElement("Negative Id not allowed !!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<Student>> findStudentByPhone(long phone) {
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		int temp=0;
		for (long i = phone; i!=0; i/=10) {
			long rem = i%10;
			temp++;
		}
		if (temp==10) {
			Student dbStudent = dao.findStudentByPhone(phone);
			if (dbStudent!=null) {
				structure.setMessage("Student found !!!");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(dbStudent);
//				return structure;
				return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.FOUND);
			} else {
//				structure.setMessage("No student found with Phone-"+phone+" !!!");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(dbStudent);
//				return structure;
				throw new NoStudentFound("No student found with "+phone);
			}
		} else {
//			structure.setMessage("Please check Phone Number !!!");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return structure;
			throw new PhoneFormat("Check phone number !!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<Student>> getStudentByEmail(String email) {
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		if (email.endsWith("@gmail.com")) {
			Student dbStudent = dao.getStudentByEmail(email);
			if (dbStudent!=null) {
				structure.setMessage("Student found !!!");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(dbStudent);
//				return structure;
				return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.FOUND);
			} else {
//				structure.setMessage("No student found with Email-"+email+" !!!");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(dbStudent);
//				return structure;
				throw new NoStudentFound("No student found with "+email);
			}
		} else {
//			structure.setMessage("Please check Email !!!");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return structure;
			throw new WrongEmailFormat("Check email !!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> findAllStudentByName(String name) {
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
		if (name.length()>=2) {
			List<Student> stdList = dao.findAllStudentByName(name);
			if (stdList.isEmpty()) {
//				structure.setMessage("No students found with Name-"+name);
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(stdList);
//				return structure;
				throw new NoStudentFound("No students found with "+name);
			} else {
				structure.setMessage("Students found with Name-"+name);
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(stdList);
//				return structure;
				return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.FOUND);
			}
		} else {
//			structure.setMessage("Please check lenght of Name !!!");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return structure;
			throw new ShortLength("Name is too short !!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> getAllStudentByaddress(String address){
		ResponseStructure<List<Student>> srtStdList = new ResponseStructure<List<Student>>();
		if (address.length()>=3) {
			List<Student> stdList = dao.getAllStudentByaddress(address);
			if (!stdList.isEmpty()) {
				srtStdList.setMessage("Students found with Address-"+address);
				srtStdList.setStatus(HttpStatus.FOUND.value());
				srtStdList.setData(stdList);
//				return srtStdList;
				return new ResponseEntity<ResponseStructure<List<Student>>>(srtStdList, HttpStatus.FOUND);
			} else {
//				srtStdList.setMessage("No students found with Address-"+address);
//				srtStdList.setStatus(HttpStatus.NOT_FOUND.value());
//				srtStdList.setData(stdList);
//				return srtStdList;
				throw new NoStudentFound("No students found with "+address);
			}
		} else {
			throw new ShortLength("Address is too short !!!");
		}
	}

	public ResponseEntity<ResponseStructure<List<Student>>> studentsBetweenMarks(int mark1, int mark2) {
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
		if (mark1>=0 || mark2>=0 || mark1>mark2) {
			List<Student> stdList = dao.studentsBetweenMarks(mark1,mark2);
	 		if (!stdList.isEmpty()) {
	 			structure.setMessage("Students found between "+mark1+" "+mark2);
	 			structure.setStatus(HttpStatus.FOUND.value());
	 			structure.setData(stdList);
//	 			return structure;
	 			return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.FOUND);
			} else {
//				structure.setMessage("No students found between "+mark1+" "+mark2);
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(stdList);
//				return structure;
				throw new NoStudentFound("No students found !!!");
			}
		} else {
			throw new WrongComparison("Wrong comparison !!!");
		}
	}

	public ResponseEntity<ResponseStructure<List<Student>>> studentsGreaterThanGrade(String grade) {
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
		char temp = grade.charAt(0);
		if (temp=='A' || temp=='B' || temp=='C' || temp=='D' || temp=='E' || temp=='E' || temp=='F') {
			List<Student> stdList = dao.findStudentsByGradeGreaterThan(grade);
			if (!stdList.isEmpty()) {
				structure.setMessage("Students found less then "+grade+" Grade");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(stdList);
//	 			return structure;
				return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.FOUND);
			} else {
//				structure.setMessage("No students found less then "+grade+" Grade");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(stdList);
//				return structure;
				throw new NoStudentFound("No students found !!!");
			}
		} else {
//			structure.setMessage("Please check Grade !!!");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return structure;
			throw new WrongGrade("Wrong grade !!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> studentsLessThanGrade(String grade) {
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
		char temp = grade.charAt(0);
		if (temp=='A' || temp=='B' || temp=='C' || temp=='D' || temp=='E' || temp=='E' || temp=='F') {
			List<Student> stdList = dao.findStudentsByGradeLessThan(grade);
			if (!stdList.isEmpty()) {
				structure.setMessage("Students found greater than "+grade+" Grade");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(stdList);
//				return structure;
				return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.FOUND);
			} else {
//				structure.setMessage("No students found greater than "+grade+" Grade");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(stdList);
//				return structure;
				throw new NoStudentFound("No students found !!!");
			}
		} else {
//			structure.setMessage("Please check Grade !!!");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return structure;
			throw new WrongGrade("Wrong grade !!!");
		}
	}

	public ResponseEntity<ResponseStructure<List<Student>>> studentsGreaterThanPercentage(double percentage) {
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
		if ( percentage>=0 && percentage<=100) {
			List<Student> stdList = dao.findStudentsByPercentageGreaterThan(percentage);
			
			if (!stdList.isEmpty()) {
				structure.setMessage("Students found greater than "+percentage+"%");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(stdList);
//				return structure;
				return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.FOUND);
			} else {
//				structure.setMessage("No students found greater than "+percentage+"%");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(stdList);
//				return structure;
				throw new NoStudentFound("No student found !!!");
			}
		} else {
//			structure.setMessage("Please check Percentage "+percentage);
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return structure;
			throw new WrongPrecentage("Wrong percentage !!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> studentsLessThanPercentage(double percentage) {
		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
		if ( percentage>=0 && percentage<=100) {
			List<Student> stdList = dao.findStudentsByPercentageLessThan(percentage);
			if (!stdList.isEmpty()) {
				structure.setMessage("Students found less than "+percentage+"%");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(stdList);
//				return structure;
				return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.FOUND);
			} else {
//				structure.setMessage("No students found less than "+percentage+"%");
//				structure.setStatus(HttpStatus.NOT_FOUND.value());
//				structure.setData(stdList);
//				return structure;
				throw new NoStudentFound("No student found !!!");
			}
		} else {
//			structure.setMessage("Please check Percentage "+percentage);
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return structure;
			throw new WrongPrecentage("Wrong percentage !!!");
		}
	}
}
