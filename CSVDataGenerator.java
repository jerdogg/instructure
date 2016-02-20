package instructure;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opencsv.CSVWriter;

public class CSVDataGenerator {

	public static Set<Student> buildValidStudents() throws IOException {

		Set<Student> studentSet = new HashSet<Student>();

		for (int i = 0; i < 5; i++) {
			Student student = new Student(i);
			student.set_name("Student Name " + i);
			student.set_courseID(i * 3);
			student.set_active(i % 2 == 0);
			studentSet.add( student );
		}

		writeCSV("./bin/instructure/inputfiles/students.csv",  toStringArrayStudents(studentSet, true) );	
		return studentSet;
	}
	

	public static Set<Course> buildValidCourses() throws IOException{
		
		Set<Course> courseSet = new HashSet<Course>();

		for (int i = 0; i < 5; i++) {
			Course course = new Course(i);
			course.set_courseName("Course Name "+i);
			course.set_state(i%2==0);
			courseSet.add(course);
		}

		writeCSV("./bin/instructure/inputfiles/classes.csv", toStringArrayCourse(courseSet, true) );	
		return courseSet;
	}
	
	public static Set<Student> buildDupStudents() throws IOException {
		
		Set<Student> studentSet = new HashSet<Student>();

		for (int i = 0; i < 5; i++) {
			Student student = new Student(i);
			student.set_name("Student Name " + i);
			student.set_courseID(i * 3);
			student.set_active(i % 2 == 0);
			studentSet.add( student );
		}
		
		List<String[]> records = toStringArrayStudents(studentSet, true);
		records.addAll(toStringArrayStudents(studentSet, false));

		writeCSV("./bin/instructure/inputfiles/students.csv", records );	
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
