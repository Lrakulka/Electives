package ua.epam.electives.entities;

public class StudentData {
    private Contract contract;
    private Course course;
    private Lecturer lecturer;

    public Contract getContract() {
	return contract;
    }

    public void setContract(Contract contract) {
	this.contract = contract;
    }

    public Course getCourse() {
	return course;
    }

    public void setCourse(Course course) {
	this.course = course;
    }

    public Lecturer getLecturer() {
	return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
	this.lecturer = lecturer;
    }
}
