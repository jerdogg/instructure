package instructure.testCases;


import static org.junit.Assert.*;
import java.util.Map;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import instructure.CSVParser;
import instructure.Course;
import instructure.DataStructure;
import instructure.Student;
import instructure.testCases.generators.CourseGenerator;
import instructure.testCases.generators.StudentGenerator;

public class AcceptanceTests {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void Print_Classes_and_Students(){
		
		try {
			StudentGenerator.buildValidStudents();
			CourseGenerator.buildValidCourses();
						
			Map<Integer, Course> courses = CSVParser.parseCourses();
			Map<Integer, Student> students = CSVParser.parseStudents();

			DataStructure data = new DataStructure(courses);
			data.addAll(students);
			data.print();
			
			data.printCourses();
			data.printSudents();
			
		} catch  (Exception e) { e.printStackTrace(); fail(e.getMessage()); }
		
	}
	
	@Test
	public void StudentUpdate(){
		
		try {
			Map<Integer, Student> Expected = StudentGenerator.buildUpdateStudents();
			Map<Integer, Student> Recieved = CSVParser.parseStudents();
			
			assertEquals("Student Update Test Failed", Expected, Recieved);
			
		} catch  (Exception e) { e.printStackTrace(); fail(e.getMessage()); }
		
	}
	
	@Test
	public void StudentsCourseUpdate(){
		
		try {
			Map<Integer, Student> Expected = StudentGenerator.buildStudentsCourseIDUpdate();
			Map<Integer, Student> Recieved = CSVParser.parseStudents();
			
			assertEquals("Student Course ID Update Test Failed", Expected, Recieved);
			
		} catch  (Exception e) { e.printStackTrace(); fail(e.getMessage()); }
		
	}
	
	@Test
	public void CourseUpdate(){
		
		try {
			Map<Integer, Course> Expected = CourseGenerator.buildUpdateCourses();			
			Map<Integer, Course> Recieved = CSVParser.parseCourses();
			
			assertEquals("Student Update Test Failed", Expected, Recieved);
			
		} catch  (Exception e) { e.printStackTrace(); fail(e.getMessage()); }	
	}
	
}
