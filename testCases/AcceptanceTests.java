package instructure.testCases;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;
import org.junit.Test;
import instructure.CSVParser;
import instructure.Course;
import instructure.DataStructure;
import instructure.Student;

public class AcceptanceTests {

	@Test
	public void ValidConditionsTest(){
		
		//TODO: clean up try catch
		try {
			StudentGenerator.buildValidStudents();
			CourseGenerator.buildValidCourses();
						
			Map<Integer, Course> courses = CSVParser.parseCourses();
			Map<Integer, Student> students = CSVParser.parseStudents();
				
			DataStructure data = new DataStructure(courses);
			data.addAll(students);
			data.print();
	
		} catch (IOException e) { e.printStackTrace(); }
	}
	
//	@Test
//	public void StudentsTest(){
//		try {
//			Map<Integer, Student> ValidStudents = StudentGenerator.buildValidStudents();			
//			Map<Integer, Student> ParsedStudents = CSVParser.parseStudents();
//				
//			assertEquals("Student Test Failed", ValidStudents, ParsedStudents);
//	
//		} catch (IOException e) { e.printStackTrace(); }
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
//		} catch (IOException e) { e.printStackTrace(); }
//	}
	
//	@Test
//	public void StudentsColumnAlignmentTest(){
//		try {
//			Map<Integer, Student> ValidStudents = StudentGenerator.BuildStudentColumnOrder();		
//			Map<Integer, Student> ParsedStudents = CSVParser.parseStudents();
//				
//			assertEquals("Duplicate Student Test Failed", ValidStudents, ParsedStudents);
//			
//			
//			CourseGenerator.buildValidCourses();			
//			Map<Integer, Course> courses = CSVParser.parseCourses();
//			
//			
//			DataStructure data = new DataStructure(courses);
//			data.addAll(ParsedStudents);
//			data.print();
//	
//		} catch (IOException e) { e.printStackTrace(); }
//	}

}
