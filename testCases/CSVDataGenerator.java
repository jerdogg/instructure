package instructure.testCases;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import com.opencsv.CSVWriter;

import instructure.Course;
import instructure.Student;

public class CSVDataGenerator {

	private static final String[] LASTNAMES = {"anderson", "johnson", "wheeler", "richards", "money", "smith"};
	private static final String[] FIRSTNAMES = {"Bill", "Ted", "Mr.", "BillyBob", "JoBob", "Steve"};
	private static final String[] COURSENAMES = {"Math", "English", "Science", "Biology"};
	
	
	public static final String studentsFileName = "../../Repro/instructure/inputFiles/students.csv";
	public static final String coursesFileName = "../../Repro/instructure/inputFiles/courses.csv";
	private static final int NUMBER_OF_RECORDS = 10;
	
	public static Set<Student> buildValidStudents() throws IOException {

		Set<Student> studentSet = new HashSet<Student>();

		for (int i = 0; i < NUMBER_OF_RECORDS; i++) {
			Student student = new Student(i);
			student.set_name(CSVDataGenerator.randomName(FIRSTNAMES, LASTNAMES));
			student.set_courseID(i * 3);
			student.set_active(i % 2 == 0);
			studentSet.add( student );
		}

		writeCSV(studentsFileName,  toStringArrayStudents(studentSet, true) );	
		return studentSet;
	}
	
	private static String randomName(String[] firstname, String[] lastname){
		return CSVDataGenerator.random(FIRSTNAMES)+" "+CSVDataGenerator.random(LASTNAMES);		
	}
	
	private static String random(String[] input) {
		int index = new Random().nextInt(input.length);
		String name = input[index];
		return name;
	}

	public static Set<Course> buildValidCourses() throws IOException{
		
		Set<Course> courseSet = new HashSet<Course>();

		for (int i = 0; i < NUMBER_OF_RECORDS; i++) {
			Course course = new Course(i);
			course.set_courseName(CSVDataGenerator.random(COURSENAMES)+" "+i);
			course.set_state(i%2==0);
			courseSet.add(course);
		}

		writeCSV(coursesFileName, toStringArrayCourse(courseSet, true) );	
		return courseSet;
	}
	
	public static Set<Student> buildDupStudents() throws IOException {
		
		Set<Student> studentSet = new HashSet<Student>();

		for (int i = 0; i < NUMBER_OF_RECORDS; i++) {
			Student student = new Student(i);
			student.set_name(CSVDataGenerator.randomName(FIRSTNAMES, LASTNAMES));
			student.set_courseID(i * 3);
			student.set_active(i % 2 == 0);
			studentSet.add( student );
		}
		
		List<String[]> records = toStringArrayStudents(studentSet, true);
		records.addAll(toStringArrayStudents(studentSet, false));

		writeCSV(studentsFileName, records );	
		return studentSet;
	}
	
	
	private static void writeCSV(String fileName, List<String[]> data) throws IOException{
		CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName), ',');
		csvWriter.writeAll(data);
		csvWriter.close();	
	}

	private static List<String[]> toStringArrayStudents(Set<Student> studentSet, boolean withHeader) {

		List<String[]> records = new ArrayList<String[]>();
		
		if (withHeader)
			records.add(new String[] { "user_id", "user_name", "couse_id", "state" });
		
		for (Student stud : studentSet ) {
			records.add(new String[] { String.valueOf(stud.get_studentId()), stud.get_name(),
					String.valueOf(stud.get_courseID()), String.valueOf(stud.is_active()) });
		}
		return records;
	}
	
	private static List<String[]> toStringArrayCourse(Set<Course> courseSet, boolean withHeader) {

		List<String[]> records = new ArrayList<String[]>();
		
		if (withHeader)
			records.add(new String[] { "course_id", "course_name", "state" });
		
		for (Course clas : courseSet ) {
			records.add(new String[] { 	String.valueOf(clas.get_courseID() ), 
										clas.get_courseName(),
										String.valueOf(clas.is_state()) });
		}
		return records;
	}
	
}
