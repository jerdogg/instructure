package instructure;

import java.util.Objects;

// ****************************************************
// Assumption:  ID can not change, but all other fields can.
// ID is keep immutable for use as key in hashmap
// ****************************************************

/*
 * 
 */
public final class Student {

	private final int _studentId;
	private String _name;
	private int _courseID;
	private boolean _active = false;
	
	public String get_name() {
		return _name;
	}

	public int hashCode(){
		return this._studentId;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public int get_studentId() {
		return _studentId;
	}

	public int get_courseID() {
		return _courseID;
	}

	public void set_courseID(int _courseID) {
		this._courseID = _courseID;
	}

	public boolean is_active() {
		return _active;
	}

	public void set_active(boolean _active) {
		this._active = _active;
	}
	
	public Student(int StudentID){
		this._studentId = StudentID;
	} 
	
	public Student(int StudentID, String name, int courseID, boolean state){
		this._studentId = StudentID;
		
	}  
	
	public void print(){
		String temp = null;
		if ( this.is_active() == false )
			temp = "deleted";
		else
			temp = "active";
		
		System.out.println(	this.get_studentId()+" "+
						  	this.get_name()+" "+
							this.get_courseID()+" "+
							temp );
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Student student = (Student) obj;
        return 	this.get_studentId() == student.get_studentId() &&        		
        		Objects.equals(this.get_name(), student.get_name()) &&
        		this.get_courseID() == student.get_courseID() &&
        		this.is_active() == student.is_active();
	}

	
}
