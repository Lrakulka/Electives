package ua.epam.electives.entities;

import java.util.List;

public class LecturerData {
    private Course course;
    private List<Contract> contracts;
    private List<Student> students;

    public Course getCourse() {
	return course;
    }

    public void setCourse(Course course) {
	this.course = course;
    }

    public List<Contract> getContracts() {
	return contracts;
    }

    public void setContracts(List<Contract> contracts) {
	this.contracts = contracts;
    }

    public List<Student> getStudents() {
	return students;
    }

    public void setStudents(List<Student> students) {
	this.students = students;
    }
}
