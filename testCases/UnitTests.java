package instructure.testCases;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import java.util.Map;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import instructure.CSVParser;
import instructure.Course;
import instructure.Student;
import instructure.testCases.generators.CourseGenerator;
import instructure.testCases.generators.StudentGenerator;
 
//*********************************************
//This test file test only the parsing of students.
//Since the Course parser follows the same logic as
//the Student parser, it stands to reason the course
//parser would pass all test cases as well
//*********************************************
public class UnitTests {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void ValidStudentsTest(){
		try {
			Map<Integer, Student> ValidStudents = StudentGenerator.buildValidStudents();			
			Map<Integer, Student> ParsedStudents = CSVParser.parseStudents();
				
			assertEquals("Student Test Failed", ValidStudents, ParsedStudents);
	
		} catch  (Exception e) { e.printStackTrace(); fail(e.getMessage()); }
	}
	
	@Test
	public void DuplicateStudentsTest(){
		try {
			Map<Integer, Student> ValidStudents = StudentGenerator.buildValidDupStudents();			
			Map<Integer, Student> ParsedStudents = CSVParser.parseStudents();
				
			assertEquals("Duplicate Student Test Failed", ValidStudents, ParsedStudents);
	
		} catch  (Exception e) { e.printStackTrace(); fail(e.getMessage()); }
	}
	
	@Test
	public void StudentsColumnAlignmentTest() {
		try {			
			Map<Integer, Student> ValidStudents = StudentGenerator.BuildStudentColumnOrder();		
			Map<Integer, Student> ParsedStudents = CSVParser.parseStudents();
				
			assertEquals("Duplicate Student Test Failed", ValidStudents, ParsedStudents);			

		} catch  (Exception e) { e.printStackTrace(); fail(e.getMessage()); }
	}

	@Test
	public void StudentsNoHeaderTest() throws Exception {
		exception.expect(Exception.class);
		exception.expectMessage(containsString("[Student Header Not Found Err]"));
				
		StudentGenerator.buildNoHeaderStudents();		
		CSVParser.parseStudents();
	}
	
	@Test
	public void StudentsEmptyTextField() throws Exception {

		exception.expect(Exception.class);
		exception.expectMessage(containsString("[Student Field Parsing Err]"));
		
		StudentGenerator.buildEmptyTextField();

		//expecting an exception
		CSVParser.parseStudents();
	}
	
	@Test
	public void StudentsEmptyTextFieldWithSpaces() throws Exception {

		exception.expect(Exception.class);
		exception.expectMessage(containsString("[Student Field Parsing Err]"));
		
		StudentGenerator.buildEmptyTextFieldWithSpaces();

		//expecting an exception
		CSVParser.parseStudents();
	}
	
	@Test
	public void StudentsInValidNumber() throws Exception {
		
		exception.expect(NumberFormatException.class);
		exception.expectMessage(containsString("For input string"));
		
		StudentGenerator.buildInValidNumeric();

		CSVParser.parseStudents();	
	}

	@Test
	public void StudentsSpacesInNumber() throws Exception {
		
		exception.expect(Exception.class);
		exception.expectMessage(containsString("[Student Field Parsing Err]"));
		
		StudentGenerator.buildSpacesInNumber();

		CSVParser.parseStudents();	
	}
	
	@Test
	public void StudentsEmptyNumber() throws Exception {
		
		exception.expect(Exception.class);
		exception.expectMessage(containsString("[Student Field Parsing Err]"));
		
		StudentGenerator.buildEmptyNumber();

		CSVParser.parseStudents();	
	}
	
	@Test
	public void StudentsEmptyRow() throws Exception {
		//Test Case:  Empty Row in CSV file
		
		//would have to change my CSV reader to accomplish it.
		//But aware of this case
	}
	
	@Test
	public void StudentsEscapeCharsInStrings() throws Exception {
		//Test Case:  escape chars in Student strings
		
		//Not implemented, but aware of this case
	}
	
	
	@Test
	public void DuplicateCoursesTest(){
		try {
			Map<Integer, Course> Expected = CourseGenerator.buildValidDupCourses();			
			Map<Integer, Course> Recieved = CSVParser.parseCourses();
				
			assertEquals("Duplicate Course Test Failed", Expected, Recieved);
	
		} catch  (Exception e) { e.printStackTrace(); fail(e.getMessage()); }
	}
	
	@Test
	public void CourseColumnAlignmentTest() {
		try {			
			Map<Integer, Course> Expected = CourseGenerator.BuildCourseColumnOrder();			
			Map<Integer, Course> Recieved = CSVParser.parseCourses();
			
			assertEquals("Duplicate Student Test Failed", Expected, Recieved);			

		} catch  (Exception e) { e.printStackTrace(); fail(e.getMessage()); }
	}
	
	@Test
	public void CoursesNoHeaderTest() throws Exception {
		exception.expect(Exception.class);
		exception.expectMessage(containsString("[Student Header Not Found Err]"));
				
		CourseGenerator.buildNoCourseHeader();		
		CSVParser.parseStudents();
	}
	
}
