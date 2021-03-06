package instructure;

import java.util.HashMap;
import java.util.Map;

public class DataStructure {

	private Map<Integer, Student> StudentEnrollment = new HashMap<Integer, Student>();	
	private Map<Integer, Course> Courses = new HashMap<Integer, Course>();
	
	public DataStructure(Map<Integer, Course> courses){		
		for( Course Clas : courses.values() ){
			Courses.put(Clas.get_courseID(), Clas );
		}
	}
	
	public void addAll(Map<Integer, Student> students){
		
		for ( Student stud : students.values() )
			this.add(stud);	
	}
	
	public void add(Student student){
		
		StudentEnrollment.put(student.get_studentId(), student);
		
		if ( !Courses.containsKey(student.get_courseID()) ) {
			System.err.println("Invalid course for Student:");
			System.err.println("  CourseID: "+student.get_courseID());
			System.err.println("  Student: "+student.get_studentId()+": "+student.get_name());
			System.err.println();
		}	
	}
	
	public void printSudents(){
	    System.out.println("Students:");
		for ( Student student : StudentEnrollment.values() ) {
			System.out.print(" "); student.print();	
		}
		System.out.println();
	}
	
	public void printCourses(){
		System.out.println("Courses:");
		for ( Course course : Courses.values() ) {
			System.out.print(" ");
			course.print();
		}
		System.out.println();
	}
	
	public void print(){
		
		for ( Course course : Courses.values() ){
			course.print();
			
			for ( Student student : StudentEnrollment.values() )
				if ( student.get_courseID() == course.get_courseID() ) {
					System.out.print("  "); 
					student.print();
				}
			System.out.println();
		}
	}
}
