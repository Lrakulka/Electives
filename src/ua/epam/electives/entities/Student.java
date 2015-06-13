package ua.epam.electives.entities;

import ua.epam.electives.entities.Entity.TableInfo;

/**
 * Class extends {@link Entity}, represent entity student.
 * @author KrabiySok
 * @version 1.0 13/06/15
 */
public class Student extends User {
    //
    public Student() {
    }

    public Student(Integer id, String fullName, String pwd) {
	super(id, fullName, pwd);
    }

    public Student(Integer id, String fullName) {
	super(id, fullName);
    }

    public Student(Student student) {
	super(student);
    }
    /**
     * Inner class extends {@link TableInfo} with type parameter
     * {@link Student}. Represent information and operation of database table
     * Student.
     * 
     * @author KrabiySok
     * @version 1.0 13/06/15
     */
    public static class StudentTableInfo extends TableInfo<Student> {
	/**
	 * Empty constructor put to super constructor object class of
	 * {@link Lecturer}.
	 */
	public StudentTableInfo() {
	    super(Student.class);
	}
    }
}
