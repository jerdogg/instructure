package instructure;

import java.util.Objects;

//****************************************************
//courseID is require to create instance of object and 
//can not change throughout the life of the object.
//
//Course object is not used as key in hash, but
//Override hashcode and equals anyways for future usage
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
		String temp = null;
		if ( this.is_state() == false )
			temp = "deleted";
		else
			temp = "active";
		
		System.out.println(	this.get_courseID()+" - "+
							this.get_courseName()+" : "+
							temp );
	}
	
}
