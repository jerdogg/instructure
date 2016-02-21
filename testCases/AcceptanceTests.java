package instructure.testCases;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Set;

import org.junit.Test;

import instructure.CSVParser;
import instructure.Course;
import instructure.DataStructure;
import instructure.Student;

public class AcceptanceTests {

	@Test
	public void ValidConditionsTest(){
		try {
			CSVDataGenerator.buildValidCourses();
			CSVDataGenerator.buildValidStudents();
			
			Set<Course> courses = CSVParser.parseCourses();
			Set<Student> students = CSVParser.parseStudents();
				
			DataStructure data = new DataStructure(courses);
			data.addAll(students);
			data.print();
	
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	@Test
	public void DuplicateStudentsTest(){
		try {
			CSVDataGenerator.buildValidCourses();
			CSVDataGenerator.buildDupStudents();
			
			Set<Course> courses = CSVParser.parseCourses();
			Set<Student> students = CSVParser.parseStudents();
				
			DataStructure data = new DataStructure(courses);
			data.addAll(students);
			data.print();
	
		} catch (IOException e) { e.printStackTrace(); }
	}

}
