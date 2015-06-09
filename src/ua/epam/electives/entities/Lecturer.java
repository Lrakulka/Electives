package ua.epam.electives.entities;

public class Lecturer extends User {

    public Lecturer() {
    }

    public Lecturer(Integer id, String full_name, String pwd) {
	super(id, full_name, pwd);
    }

    public Lecturer(Integer id, String full_name) {
	super(id, full_name);
    }

    public Lecturer(Lecturer lecturer) {
	super(lecturer);
    }

    public static class LecturerTableInfo extends TableInfo<Lecturer> {

	public LecturerTableInfo() {
	    super(Lecturer.class);
	}
    }
}
