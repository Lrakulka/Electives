package ua.epam.electives.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.electives.dao.DaoFactory;
import ua.epam.electives.entities.Course;
import ua.epam.electives.entities.Lecturer;
import ua.epam.electives.maneger.ConfigurationManager;

public class AllCoursesCommand implements Command {
    public static final String COMMAND_TYPE = "allCourses";
    public static final String COURSES_DATA = "coursesData";

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String page = null;
	DaoFactory daoFactory = DaoFactory.getDaoFactory();
	HashMap<String, ArrayList<Course>> coursesData = new HashMap<>();
	ArrayList<Lecturer> lectures = (ArrayList<Lecturer>) daoFactory
		.getLecturerDao().getAll();
	ArrayList<Course> courses;
	for (Lecturer lecturer : lectures) {
	    courses = (ArrayList<Course>) daoFactory.getCourseDao()
		    .getLecturerCourses(lecturer.getId());
	    coursesData.put(lecturer.getFullName(), courses);
	}
	request.setAttribute(COURSES_DATA, coursesData);
	page = ConfigurationManager.getInstance().getProperty(
		ConfigurationManager.STUDENT_ALL_COURSES_PAGE_PATH);
	return page;
    }

    @Override
    public String getCommandType() {
	// TODO Auto-generated method stub
	return COMMAND_TYPE;
    }

}
