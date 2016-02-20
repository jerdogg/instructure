package instructure;

import java.util.Objects;

//****************************************************
//Assumption:  ID can not change, but all other fields can.
//ID is keep immutable for use as key in hashmap
//****************************************************
public final class Course {

	private final int _courseID;
	private String _courseName;
	private boolean _state = false;
	
	public Course(int courseID){;
		this._courseID = courseID;
	}
	
	public Course(int courseID, String courseName, boolean state){
		this._courseID = courseID;
		this._courseName = courseName;
		this._state = state;		
	}
	
	public int hashCode(){
		return this._courseID;
	}

	public String get_courseName() {
		return _courseName;
	}

	public void set_courseName(String _courseName) {
		this._courseName = _courseName;
	}

	public boolean is_state() {
		return _state;
	}

	public void set_state(boolean _state) {
		this._state = _state;
	}

	public int get_courseID() {
		return _courseID;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Course course = (Course) obj;
        return 	this.get_courseID() == course.get_courseID() &&        		
        		Objects.equals(this.get_courseName(), course.get_courseName() ) &&        		
        		this.is_state() == course.is_state();
	}
	
	public void print(){
		System.out.println(	this.get_courseID()+" - "+
							this.get_courseName()+" : "+
							this.is_state());
	}
	
}
