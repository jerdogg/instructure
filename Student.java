package instructure;

import java.util.Objects;

//****************************************************
//StudentID is require to create instance of object and 
//can not change throughout the life of the object.
//
//Student object is not used as key in hash, but
//Override hashcode and equals anyways for future usage
//****************************************************
public final class Student {

	private final int _studentId;
	private String _name;
	private int _courseID;
	private boolean _active = false;
	
	public Student(int StudentID){
		this._studentId = StudentID;
	} 
	
	public Student(int StudentID, String name, int courseID, boolean state){
		this._studentId = StudentID;
		this._name = name;
		this._courseID = courseID;
		this._active = state;		
	}
	
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
