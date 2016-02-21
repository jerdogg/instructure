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

public class CourseGenerator {

	private static final String[] COURSENAMES = { "Math", "English", "Science", "Biology" };
	public static final String coursesFileName = "../../Repro/instructure/inputFiles/courses.csv";

	private static final int NUMBER_OF_RECORDS = 10;

	public static Set<Course> buildValidCourses() throws IOException {

		Set<Course> courseSet = new HashSet<Course>();

		for (int i = 0; i < NUMBER_OF_RECORDS; i++) {
			Course course = new Course(i);
			course.set_courseName(CourseGenerator.random(COURSENAMES) + " " + i);
			course.set_state(i % 2 == 0);
			courseSet.add(course);
		}

		writeCSV(coursesFileName, toStringArrayCourse(courseSet, true));
		return courseSet;
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
	
	
	private static void writeCSV(String fileName, List<String[]> data) throws IOException{
		CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName), ',');
		csvWriter.writeAll(data);
		csvWriter.close();	
	}
	
	private static String random(String[] input) {
		int index = new Random().nextInt(input.length);
		String name = input[index];
		return name;
	}
	
}
