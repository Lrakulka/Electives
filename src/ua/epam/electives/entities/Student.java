package ua.epam.electives.entities;

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

    public static class StudentTableInfo extends TableInfo<Student> {

	public StudentTableInfo() {
	    super(Student.class);
	}
    }
}
