package instructure;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import com.opencsv.CSVReader;


public class CSVParser {

	public static Set<Student> parseStudents() throws IOException {

		CSVReader reader = new CSVReader(new FileReader("./bin/instructure/inputfiles/students.csv"), ',');
		Set<Student> StudentSet = new HashSet<Student>();
		String[] record = null;
		// Ignore header
		reader.readNext();

		while ((record = reader.readNext()) != null) {
			Student stud = new Student(Integer.valueOf(record[0]));
			stud.set_name(record[1]);
			stud.set_courseID(Integer.valueOf(record[2]));
			stud.set_active(Boolean.parseBoolean(record[3]));
			StudentSet.add( stud );
		}
		reader.close();
		return StudentSet;
	}
	
	public static Set<Course> parseClasses() throws IOException {

		CSVReader reader = new CSVReader(new FileReader("./bin/instructure/inputfiles/classes.csv"), ',');
		Set<Course> ClassSet = new HashSet<Course>();
		String[] record = null;
		// Ignore header
		reader.readNext();

		while ((record = reader.readNext()) != null) {
			Course course = new Course(Integer.valueOf(record[0]));
			course.set_courseName(record[1]);
			course.set_state(Boolean.parseBoolean(record[2]));
			ClassSet.add(course);
		}
		reader.close();
		return ClassSet;
	}
}
