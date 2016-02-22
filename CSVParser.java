package instructure;

import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.opencsv.CSVReader;
import instructure.testCases.CourseGenerator;
import instructure.testCases.StudentGenerator;


public class CSVParser {
	
	
	public static Map<Integer, Student> parseStudents() throws Exception {
		
		//throws IOexceptin if file not found.
		CSVReader reader = new CSVReader(new FileReader(StudentGenerator.studentsFileName), ',');
		Map<Integer, Student> StudentMap = new HashMap<Integer, Student>();
		String[] record = null;		
		
		//Read header
		String[] Header = reader.readNext();
		
		//Check Header Length
		if ( Header.length > 4 ) {	
			reader.close();
			throw new Exception("[Student Header Size Err]: Unexpected Number of Columns in Student CVS file");
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
					reader.close();
					throw new Exception("[Student Header Not Found Err]: Unknown Column Name "+Header[i]);
				}					
			}
		}

		//Read records and Map accordingly to student object
		//Can throw int parsing error if string isn't a int.
		while ((record = reader.readNext()) != null) {	
			
			//Checks for:
			//empty string in record
			//Just spaces in record
			//A number for a Name is valid.  
			//any text for state other than true is considered false.
			for (String text : record) {				
				if ( text == null || text.trim().isEmpty() ) {
					reader.close();
					throw new Exception("[Student Field Parsing Err]: Null text for student: "+Arrays.toString(record));
				}					
			}
			
			Student stud = new Student(Integer.valueOf(record[mapping.get("user_id")]));				
			stud.set_name(record[mapping.get("user_name")]);				
			stud.set_courseID(Integer.valueOf(record[mapping.get("course_id")]));
			stud.set_active(Boolean.parseBoolean(record[mapping.get("state")]));				
			StudentMap.put(stud.get_studentId(), stud );			
		}
		
		reader.close();
		return StudentMap;
	}
	
	public static Map<Integer, Course> parseCourses() throws Exception {

		//Throws IOException if file not found
		CSVReader reader = new CSVReader(new FileReader(CourseGenerator.coursesFileName), ',');
		Map<Integer, Course> CourseMap = new HashMap<Integer, Course>();
		String[] record = null;		
		
		//Read header
		String[] Header = reader.readNext();
		
		//Check Header Length
		if ( Header.length > 3 ) {
			reader.close();
			throw new Exception("[Course Header Size Err]: Unexpected Number of Columns in Student CVS file");
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
					reader.close();
					throw new Exception("[Course Header Not Found Err]: Unknown Column Name "+Header[i]);
				}					
			}
		}

		//Read records and Map accordingly to student object
		//Can throw int parsing error if string isn't a int.
		while ((record = reader.readNext()) != null) {
			
			//Checks for:
			//empty string in record
			//Just spaces in record
			//A number for a Name is valid.  
			//any text for state other than true is considered false.
			for (String text : record) {				
				if ( text == null || text.trim().isEmpty() ) {
					reader.close();
					throw new Exception("[Course Field Parsing Err]: Null text for Course: "+Arrays.toString(record));
				}					
			}
			
			Course course = new Course(Integer.valueOf(record[mapping.get("course_id")]));
			course.set_courseName(record[mapping.get("course_name")]);
			course.set_state(Boolean.parseBoolean(record[mapping.get("state")]));
			CourseMap.put(course.get_courseID(), course);
		}
		reader.close();
		return CourseMap;
	}

}
