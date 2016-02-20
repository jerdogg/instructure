package instructure.testCases;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.Set;
import org.junit.Test;
import instructure.CSVDataGenerator;
import instructure.CSVParser;
import instructure.Course;
import instructure.Student;


public class UnitTests {

	@Test
	public void CSV_Input_Output_Test(){
		try {
			Set<Student> Input = CSVDataGenerator.buildValidStudents();
			Set<Student> Output = CSVParser.parseStudents();
				
			assertEquals("not equal", Input, Output);
	
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	@Test
	public void CSV_Dups_Test2(){
		try {
			//dups will be put in the csv file.  
			//this verifies that dups are not allowed into the set
			//verifying hashcode and equals are functioning
			Set<Student> Input = CSVDataGenerator.buildDupStudents();
			Set<Student> Output = CSVParser.parseStudents();
				
			assertEquals("not equal", Input, Output);
					
		} catch (IOException e) { e.printStackTrace(); }
	}

	@Test
	public void GenParseCoursesTest(){
		try {
			Set<Course> Input = CSVDataGenerator.buildValidCourses();
			Set<Course> Output = CSVParser.parseClasses();
				
			assertEquals("not equal", Input, Output);
					
		} catch (IOException e) { e.printStackTrace(); }
	}
	
}
