package instructure.testCases;


import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.*;

import instructure.CSVParser;
import instructure.Course;
import instructure.DataStructure;
import instructure.Student;

public class AcceptanceTests {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
//	@Test
//	public void Print_Classes_and_Students(){
//		
//		try {
//			StudentGenerator.buildValidStudents();
//			CourseGenerator.buildValidCourses();
//						
//			Map<Integer, Course> courses = CSVParser.parseCourses();
//			Map<Integer, Student> students = CSVParser.parseStudents();
//
//			DataStructure data = new DataStructure(courses);
//			data.addAll(students);
//			data.print();
//			
//			data.printCourses();
//			data.printSudents();
//			
//		} catch  (Exception e) { e.printStackTrace(); fail(e.getMessage()); }
//		
//	}
//	
//	@Test
//	public void ValidStudentsTest(){
//		try {
//			Map<Integer, Student> ValidStudents = StudentGenerator.buildValidStudents();			
//			Map<Integer, Student> ParsedStudents = CSVParser.parseStudents();
//				
//			assertEquals("Student Test Failed", ValidStudents, ParsedStudents);
//	
//		} catch  (Exception e) { e.printStackTrace(); fail(e.getMessage()); }
//	}
//	
//	@Test
//	public void DuplicateStudentsTest(){
//		try {
//			Map<Integer, Student> ValidStudents = StudentGenerator.buildValidDupStudents();			
//			Map<Integer, Student> ParsedStudents = CSVParser.parseStudents();
//				
//			assertEquals("Duplicate Student Test Failed", ValidStudents, ParsedStudents);
//	
//		} catch  (Exception e) { e.printStackTrace(); fail(e.getMessage()); }
//	}
//	
	@Test
	public void StudentsColumnAlignmentTest() {
		try {			
			Map<Integer, Student> ValidStudents = StudentGenerator.BuildStudentColumnOrder();		
			Map<Integer, Student> ParsedStudents = CSVParser.parseStudents();
				
			assertEquals("Duplicate Student Test Failed", ValidStudents, ParsedStudents);			

		} catch  (Exception e) { e.printStackTrace(); fail(e.getMessage()); }
	}
	
	@Test
	public void StudentsEmptyTextField() throws Exception {

		Map<Integer, Student> ValidStudents = StudentGenerator.buildEmptyTextField();

		//expecting an exception
		Map<Integer, Student> ParsedStudents = CSVParser.parseStudents();

		exception.expect(Exception.class);
		exception.expectMessage(containsString("[Student Field Parsing err]t"));
	}
	
//	@Test
//	public void StudentsInValidNumber(){
//		try {			
//			Map<Integer, Student> ValidStudents = StudentGenerator.BuildStudentColumnOrder();		
//			Map<Integer, Student> ParsedStudents = CSVParser.parseStudents();
//				
//			assertEquals("Duplicate Student Test Failed", ValidStudents, ParsedStudents);			
//
//		} catch  (Exception e) { e.printStackTrace(); fail(e.getMessage()); }
//	}

}
