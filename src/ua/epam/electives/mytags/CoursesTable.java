package ua.epam.electives.mytags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ua.epam.electives.entities.Course;

public class CoursesTable extends TagSupport {

    /**
     * 
     */
    private static final long serialVersionUID = -7385266105655041083L;
    private static final Logger LOGGER = Logger.getLogger(CoursesTable.class);
    private String noCourses;
    private String lecturerName;
    private String courseName;
    private String buttonSubscribe;
    private HashMap<String, ArrayList<Course>> coursesData;

    public void setNoCourses(String noCourses) {
	this.noCourses = noCourses;
    }

    public void setLecturerName(String lecturerName) {
	this.lecturerName = lecturerName;
    }

    public void setCourseName(String courseName) {
	this.courseName = courseName;
    }

    public void setButtonSubscribe(String buttonSubscribe) {
	this.buttonSubscribe = buttonSubscribe;
    }

    public void setCoursesData(HashMap<String, ArrayList<Course>> coursesData) {
	this.coursesData = coursesData;
    }

    public int doStartTag() {
	StringBuilder tableBuilder = new StringBuilder(
		"<table border=\"2\" cellpadding=\"8\">");
	int i = 0;
	ArrayList<Course> courses;
	if (coursesData.isEmpty()) {
	    tableBuilder.append("<tr><td>" + noCourses + "</td></tr>");
	} else {
	    tableBuilder
		    .append("<form name=\"LecturerTableForm\" action=\"controller\" "
			    + "method=\"post\"><input type=\"hidden\" name=\"command\" "
			    + "value=\"subscribeCourse\" />");
	    tableBuilder.append("<tr><td>№</td><td>" + lecturerName + "</td><td>"
		    + courseName + "</td></tr>");
	    for (String lector : coursesData.keySet()) {
		courses = coursesData.get(lector);
		if (courses == null || courses.isEmpty()) {
		    tableBuilder.append("<tr><td>" + (i + 1) + "</td><td>"
			    + lector + "</td><td>" + noCourses + "</td></tr>");
		    i++;
		} else {
		    for (int j = 0; j < courses.size(); ++i, ++j) {
			tableBuilder.append("<tr><td>" + (i + 1) + "</td><td>"
				+ lector + "</td><td>"
				+ courses.get(j).getName() + "</td>");

			tableBuilder
				.append("<td><button name=\"courseId\" value=\""
					+ courses.get(j).getId()
					+ "\">"
					+ buttonSubscribe + "</button></td>");
			tableBuilder.append("</tr>");
		    }
		}
	    }
	    tableBuilder.append("</form>");
	}
	tableBuilder.append("</table>");
	try {
	    pageContext.getOut().write(tableBuilder.toString());
	} catch (IOException e) {
	    LOGGER.error("Problem in courses table(tag)", e);
	}
	return SKIP_BODY;
    }
}
