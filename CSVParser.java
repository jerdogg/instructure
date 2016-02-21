package instructure;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.opencsv.CSVReader;
import instructure.testCases.CourseGenerator;
import instructure.testCases.StudentGenerator;


public class CSVParser {

	
	
	public static Map<Integer, Student> parseStudents() throws IOException {
		
		CSVReader reader = new CSVReader(new FileReader(StudentGenerator.studentsFileName), ',');
		Map<Integer, Student> StudentMap = new HashMap<Integer, Student>();
		String[] record = null;		
		
		//Read header
		String[] Header = reader.readNext();
		
		//Check Header Length
		if ( Header.length > 4 ) {
			System.err.println("[Student Parsing err]: Unexpected Number of Columns Student CVS file");
			reader.close();
			return null;
		}		
		
		//Map column name to column order
		Map<String, Integer> mapping = new HashMap<String, Integer>();
		//FYI: typical column order: "user_id", "user_name", "couse_id", "state"
		
		//Map function
		for (int i=0; i<Header.length; i++){
			switch ( Header[i] ) {
				case "user_id": mapping.put("user_id", i); break;
				case "user_name": mapping.put("user_name", i); break;
				case "course_id": mapping.put("course_id", i); break;
				case "state": mapping.put("state", i); break;
				default: {
					System.err.println("[Student Parsing err]: Unexpected Column Name "+Header[i]);
					reader.close();
					return null;
				}					
			}
		}

		//Read records and Map accordingly to student object
		while ((record = reader.readNext()) != null) {	
			Student stud = new Student(Integer.valueOf(record[mapping.get("user_id")]));
			stud.set_name(record[mapping.get("user_name")]);
			stud.set_courseID(Integer.valueOf(record[mapping.get("course_id")]));
			stud.set_active(Boolean.parseBoolean(record[mapping.get("state")]));
			StudentMap.put(stud.get_studentId(), stud );			
		}
		
		reader.close();
		return StudentMap;
	}
	
	public static Map<Integer, Course> parseCourses() throws IOException {

		CSVReader reader = new CSVReader(new FileReader(CourseGenerator.coursesFileName), ',');
		Map<Integer, Course> CourseMap = new HashMap<Integer, Course>();
		String[] record = null;		
		
		//Read header
		String[] Header = reader.readNext();
		
		//Check Header Length
		if ( Header.length > 3 ) {
			System.err.println("[Courses Parsing err]: Unexpected Number of Columns Courses CVS file");
			reader.close();
			return null;
		}		
		
		//Map column name to column order
		Map<String, Integer> mapping = new HashMap<String, Integer>();
		//FYI: typical column order: "course_id", "course_name", "state"
		
		//Map function
		for (int i=0; i<Header.length; i++){
			switch ( Header[i] ) {
				case "course_id": mapping.put("course_id", i); break;
				case "course_name": mapping.put("course_name", i); break;
				case "state": mapping.put("state", i); break;
				default: {
					System.err.println("[Courses Parsing err]: Unexpected Column Name: "+Header[i]);
					reader.close();
					return null;
				}					
			}
		}

		//Read records and Map accordingly to student object
		while ((record = reader.readNext()) != null) {
			Course course = new Course(Integer.valueOf(record[mapping.get("course_id")]));
			course.set_courseName(record[mapping.get("course_name")]);
			course.set_state(Boolean.parseBoolean(record[mapping.get("state")]));
			CourseMap.put(course.get_courseID(), course);
		}
		reader.close();
		return CourseMap;
	}
}
