package instructure.testCases;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import com.opencsv.CSVWriter;
import instructure.Course;

public class CourseGenerator {

	private static final String[] COURSENAMES = { "Math", "English", "Science", "Biology" };
	public static final String coursesFileName = "../../Repro/instructure/inputFiles/courses.csv";

	private static final int NUMBER_OF_RECORDS = 10;

	private static Map<Integer, Course> CourseBuilder() {

		Map<Integer, Course> courseSet = new HashMap<Integer, Course>();

		for (int i = 0; i < NUMBER_OF_RECORDS; i++) {
			Course course = new Course(i);
			course.set_courseName(CourseGenerator.random(COURSENAMES) + " " + i);
			course.set_state(i % 2 == 0);
			courseSet.put(course.get_courseID(), course);
		}

		return courseSet;
	}
	
	public static Map<Integer, Course> buildValidCourses() throws IOException {
		
		Map<Integer, Course> courseSet = CourseGenerator.CourseBuilder();
		
		writeCSV(coursesFileName,  toStringArrayCourse(courseSet, true) );	
		return courseSet;
	}	
	
	public static Map<Integer, Course> buildValidDupCourses() throws IOException {
		
		Map<Integer, Course> courseSet = CourseGenerator.CourseBuilder();
				
		List<String[]> records = toStringArrayCourse(courseSet, true);
		records.addAll(toStringArrayCourse(courseSet, false));

		writeCSV(coursesFileName, records );	
		return courseSet;
	}
	
	public static Map<Integer, Course> BuildCourseColumnOrder() throws IOException {
		
		Map<Integer, Course> courseSet = CourseGenerator.CourseBuilder();		
		
		List<String[]> records = new ArrayList<String[]>();
		records.add(new String[] { "course_name", "state", "course_id" });

		for (Course clas : courseSet.values() ) {		
			records.add(new String[] { 	clas.get_courseName(),
					String.valueOf(clas.is_state()),
					String.valueOf(clas.get_courseID()) });
		}
		writeCSV(coursesFileName, records );	
		return courseSet;
	}
		
	private static List<String[]> toStringArrayCourse(Map<Integer, Course> courseSet, boolean withHeader) {
		
		List<String[]> records = new ArrayList<String[]>();
		
		if (withHeader) 
			records.add(new String[] { "course_id", "course_name", "state" });
				
		
		//No header - used for Dups
		for (Course clas : courseSet.values() ) {
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
