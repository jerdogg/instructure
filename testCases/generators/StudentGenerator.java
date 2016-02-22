package instructure.testCases.generators;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import com.opencsv.CSVWriter;
import instructure.Student;

public class StudentGenerator {

	private static final String[] LASTNAMES = {"anderson", "johnson", "wheeler", "richards", "money", "smith"};
	private static final String[] FIRSTNAMES = {"Bill", "Ted", "Mr.", "BillyBob", "JoBob", "Steve"};
	
	public static final String STUDENTS_FILE_NAME = "../../Repro/instructure/inputFiles/students.csv";	
	public static final int NUMBER_OF_RECORDS = 30;
	
	private static Map<Integer, Student> StudentBuilder() {

		Map<Integer, Student> studentSet = new HashMap<Integer, Student>();

		for (int i = 0; i < NUMBER_OF_RECORDS; i++) {
			Student student = new Student(i);
			student.set_name(StudentGenerator.randomName(FIRSTNAMES, LASTNAMES));
			//Random course ID from available courses plus 5 (so we have a few invalid courses)
			student.set_courseID(new Random().nextInt( CourseGenerator.NUMBER_OF_RECORDS + 5 ) );
			student.set_active(i % 2 == 0);
			studentSet.put(student.get_studentId(), student );
		}

		return studentSet;
	}
	
	public static Map<Integer, Student> buildValidStudents() throws IOException {
		
		Map<Integer, Student> studentSet = StudentGenerator.StudentBuilder();
		
		writeCSV(STUDENTS_FILE_NAME,  toStringArrayStudents(studentSet, true) );	
		return studentSet;
	}
	
	public static Map<Integer, Student> buildNoHeaderStudents() throws IOException {
		
		Map<Integer, Student> studentSet = StudentGenerator.StudentBuilder();
		
		writeCSV(STUDENTS_FILE_NAME,  toStringArrayStudents(studentSet, false) );	
		return studentSet;
	}
	
	public static Map<Integer, Student> buildUpdateStudents() throws IOException {
		
		Map<Integer, Student> studentSet = StudentGenerator.StudentBuilder();		
		
		List<String[]> records = new ArrayList<String[]>();
		records.add(new String[] { "user_id", "user_name", "course_id", "state" });

		for (Student stud : studentSet.values() ) {		
			records.add(new String[] { 	String.valueOf(stud.get_studentId()), 
										stud.get_name(),
										String.valueOf(stud.get_courseID()), 
										String.valueOf(stud.is_active()) });
		}
		
		studentSet = StudentGenerator.StudentBuilder();
		for (Student stud : studentSet.values() ) {		
			records.add(new String[] { 	String.valueOf(stud.get_studentId()), 
										stud.get_name(),
										String.valueOf(stud.get_courseID()), 
										String.valueOf(stud.is_active()) });
		}
		
		writeCSV(STUDENTS_FILE_NAME, records );	
		return studentSet;
	}
	
	public static Map<Integer, Student> buildStudentsCourseIDUpdate() throws IOException {
		
		Map<Integer, Student> studentSet = StudentGenerator.StudentBuilder();		
		
		List<String[]> records = new ArrayList<String[]>();
		records.add(new String[] { "user_id", "user_name", "course_id", "state" });

		for (Student stud : studentSet.values() ) {		
			records.add(new String[] { 	String.valueOf(stud.get_studentId()), 
										stud.get_name(),
										String.valueOf(stud.get_courseID()), 
										String.valueOf(stud.is_active()) });
		}		
		
		for (Student stud : studentSet.values() ) {		
			int newCourseNumber = new Random().nextInt( CourseGenerator.NUMBER_OF_RECORDS );
			records.add(new String[] { 	String.valueOf(stud.get_studentId()), 
										stud.get_name(),
										String.valueOf(newCourseNumber), 
										String.valueOf(stud.is_active()) });
			stud.set_courseID( newCourseNumber );
		}
		
		writeCSV(STUDENTS_FILE_NAME, records );	
		return studentSet;
	}

	public static Map<Integer, Student> buildValidDupStudents() throws IOException {
		
		Map<Integer, Student> studentSet = StudentGenerator.StudentBuilder();
				
		List<String[]> records = toStringArrayStudents(studentSet, true);
		records.addAll(toStringArrayStudents(studentSet, false));

		writeCSV(STUDENTS_FILE_NAME, records );	
		return studentSet;
	}
	
	public static Map<Integer, Student> buildEmptyTextField() throws IOException {
		
		Map<Integer, Student> studentSet = StudentGenerator.StudentBuilder();
				
		Student InvalidStudent = studentSet.get( new Random().nextInt( studentSet.size() ) );
		InvalidStudent.set_name("");
		
		List<String[]> records = toStringArrayStudents(studentSet, true);

		writeCSV(STUDENTS_FILE_NAME, records );	
		return studentSet;
	}
	
public static Map<Integer, Student> buildEmptyTextFieldWithSpaces() throws IOException {
		
		Map<Integer, Student> studentSet = StudentGenerator.StudentBuilder();
				
		Student InvalidStudent = studentSet.get( new Random().nextInt( studentSet.size() ) );
		InvalidStudent.set_name("   ");
		
		List<String[]> records = toStringArrayStudents(studentSet, true);

		writeCSV(STUDENTS_FILE_NAME, records );	
		return studentSet;
	}
	
	public static Map<Integer, Student> buildInValidNumeric() throws IOException {
		
		Map<Integer, Student> studentSet = StudentGenerator.StudentBuilder();		
		
		List<String[]> records = new ArrayList<String[]>();
		records.add(new String[] { "user_name", "user_id", "state", "course_id" });

		for (Student stud : studentSet.values() ) {		
			records.add(new String[] { 	String.valueOf("Not A Number"), 
										stud.get_name(),
										String.valueOf(stud.get_courseID()), 
										String.valueOf(stud.is_active()) });
		}
		writeCSV(STUDENTS_FILE_NAME, records );	
		return studentSet;
	}
	

	public static Map<Integer, Student> buildSpacesInNumber() throws IOException {
		
		Map<Integer, Student> studentSet = StudentGenerator.StudentBuilder();		
		
		List<String[]> records = new ArrayList<String[]>();
		records.add(new String[] { "user_name", "user_id", "state", "course_id" });

		for (Student stud : studentSet.values() ) {		
			records.add(new String[] { 	String.valueOf("   "), 
										stud.get_name(),
										String.valueOf(stud.get_courseID()), 
										String.valueOf(stud.is_active()) });
		}
		writeCSV(STUDENTS_FILE_NAME, records );	
		return studentSet;
	}
	
public static Map<Integer, Student> buildEmptyNumber() throws IOException {
		
		Map<Integer, Student> studentSet = StudentGenerator.StudentBuilder();		
		
		List<String[]> records = new ArrayList<String[]>();
		records.add(new String[] { "user_name", "user_id", "state", "course_id" });

		for (Student stud : studentSet.values() ) {		
			records.add(new String[] { 	null, 
										stud.get_name(),
										String.valueOf(stud.get_courseID()), 
										String.valueOf(stud.is_active()) });
		}
		writeCSV(STUDENTS_FILE_NAME, records );	
		return studentSet;
	}
	
	public static Map<Integer, Student> buildBlankRow() throws IOException {

		Map<Integer, Student> studentSet = StudentGenerator.StudentBuilder();
		String[] emptyRow = new String[]{"", "", "", ""} ;
		
		List<String[]> records = new ArrayList<String[]>();
		records.add(new String[] { "user_name", "user_id", "state", "course_id" });

		for (Student stud : studentSet.values() ) {		
			if ( stud.get_studentId()%2 == 0 )
				records.add(emptyRow);
			else 
			records.add(new String[] { 	String.valueOf(stud.get_studentId()), 
										stud.get_name(),
										String.valueOf(stud.get_courseID()), 
										String.valueOf(stud.is_active()) });
		}
		
		
		writeCSV(STUDENTS_FILE_NAME, records);
		return studentSet;
	}

	public static Map<Integer, Student> BuildStudentColumnOrder() throws IOException {
		
		Map<Integer, Student> studentSet = StudentGenerator.StudentBuilder();		
		
		List<String[]> records = new ArrayList<String[]>();
		records.add(new String[] { "user_name", "user_id", "state", "course_id" });

		for (Student stud : studentSet.values() ) {		
			records.add(new String[] { 	stud.get_name(),
					String.valueOf(stud.get_studentId()),
					String.valueOf(stud.is_active()),
					String.valueOf(stud.get_courseID()) });
		}
		writeCSV(STUDENTS_FILE_NAME, records );	
		return studentSet;
	}
	
	
	private static void writeCSV(String fileName, List<String[]> data) throws IOException{
		CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName), ',');
		csvWriter.writeAll(data);
		csvWriter.close();	
	}

	private static List<String[]> toStringArrayStudents(Map<Integer, Student> studentSet, boolean withHeader) {

		List<String[]> records = new ArrayList<String[]>();

		if (withHeader)
			// Normal construction
			records.add(new String[] { "user_id", "user_name", "course_id", "state" });

		// No header option - used for Dups
		for (Student stud : studentSet.values()) {
			records.add(new String[] { String.valueOf(stud.get_studentId()), stud.get_name(),
					String.valueOf(stud.get_courseID()), String.valueOf(stud.is_active()) });
		}

		return records;
	}

	
	private static String randomName(String[] firstname, String[] lastname){
		return StudentGenerator.random(FIRSTNAMES)+" "+StudentGenerator.random(LASTNAMES);		
	}
	
	private static String random(String[] input) {
		int index = new Random().nextInt(input.length);
		String name = input[index];
		return name;
	}
	
}
