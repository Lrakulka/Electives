package ua.epam.electives.entities;

public class AuthorizedUser extends User {
    private boolean isLecturer;

    public AuthorizedUser(Integer id, String fullName, String pwd,
	    boolean isLecturer) {
	super(id, fullName, pwd);
	this.isLecturer = isLecturer;
    }

    public AuthorizedUser(String name, String pwd) {
	super(-1, name, pwd);
    }

    /**
     * @return the isLecturer
     */
    public boolean isLecturer() {
	return isLecturer;
    }

    /**
     * @param isLecturer
     *            the isLecturer to set
     */
    public void setLecturer(boolean isLecturer) {
	this.isLecturer = isLecturer;
    }
}
