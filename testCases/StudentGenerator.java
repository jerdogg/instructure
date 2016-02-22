package instructure.testCases;

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
	
	public static final String studentsFileName = "../../Repro/instructure/inputFiles/students.csv";	
	public static final int NUMBER_OF_RECORDS = 30;
	
	private static Map<Integer, Student> StudentBuilder() {

		Map<Integer, Student> studentSet = new HashMap<Integer, Student>();

		for (int i = 0; i < NUMBER_OF_RECORDS; i++) {
			Student student = new Student(i);
			student.set_name(StudentGenerator.randomName(FIRSTNAMES, LASTNAMES));
			student.set_courseID(new Random().nextInt( CourseGenerator.NUMBER_OF_RECORDS + 5 ) );
			student.set_active(i % 2 == 0);
			studentSet.put(student.get_studentId(), student );
		}

		return studentSet;
	}
	
	public static Map<Integer, Student> buildValidStudents() throws IOException {
		
		Map<Integer, Student> studentSet = StudentGenerator.StudentBuilder();
		
		writeCSV(studentsFileName,  toStringArrayStudents(studentSet, true) );	
		return studentSet;
	}

	public static Map<Integer, Student> buildValidDupStudents() throws IOException {
		
		Map<Integer, Student> studentSet = StudentGenerator.StudentBuilder();
				
		List<String[]> records = toStringArrayStudents(studentSet, true);
		records.addAll(toStringArrayStudents(studentSet, false));

		writeCSV(studentsFileName, records );	
		return studentSet;
	}
	
	public static Map<Integer, Student> buildEmptyTextField() throws IOException {
		
		Map<Integer, Student> studentSet = StudentGenerator.StudentBuilder();
				
		Student InvalidStudent = studentSet.get( new Random().nextInt( studentSet.size() ) );
		InvalidStudent.set_name("");
		
		List<String[]> records = toStringArrayStudents(studentSet, true);

		writeCSV(studentsFileName, records );	
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
		writeCSV(studentsFileName, records );	
		return studentSet;
	}
	
	
	private static void writeCSV(String fileName, List<String[]> data) throws IOException{
		CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName), ',');
		csvWriter.writeAll(data);
		csvWriter.close();	
	}

	private static List<String[]> toStringArrayStudents(Map<Integer, Student> studentSet, boolean withHeader) {
		
		List<String[]> records = new ArrayList<String[]>();
		
		if (withHeader) {
				//Normal construction
				records.add(new String[] { "user_id", "user_name", "course_id", "state" });
				for (Student stud : studentSet.values() ) {
					records.add(new String[] { 	String.valueOf(stud.get_studentId()), 
												stud.get_name(),
												String.valueOf(stud.get_courseID()), 
												String.valueOf(stud.is_active()) });
				}
		} else {
			//No header option - used for Dups
			for (Student stud : studentSet.values() ) {
				records.add(new String[] { String.valueOf(stud.get_studentId()), stud.get_name(),
						String.valueOf(stud.get_courseID()), String.valueOf(stud.is_active()) });
			}
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
