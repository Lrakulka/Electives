package ua.epam.electives.entities;

import ua.epam.electives.entities.Entity.TableInfo;

/**
 * Class extends {@link Entity}, represent entity lecturer.
 * @author KrabiySok
 * @version 1.0 13/06/15
 */
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

    /**
     * Inner class extends {@link TableInfo} with type parameter
     * {@link Lecturer}. Represent information and operation of database table
     * Lecturer.
     * 
     * @author KrabiySok
     * @version 1.0 13/06/15
     */
    public static class LecturerTableInfo extends TableInfo<Lecturer> {
	/**
	 * Empty constructor put to super constructor object class of
	 * {@link Lecturer}.
	 */
	public LecturerTableInfo() {
	    super(Lecturer.class);
	}
    }
}
