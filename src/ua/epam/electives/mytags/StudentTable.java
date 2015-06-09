package ua.epam.electives.mytags;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ua.epam.electives.entities.StudentData;

public class StudentTable extends TagSupport {

    /**
     * 
     */
    private static final long serialVersionUID = -2104961797473796800L;
    private static final Logger LOGGER = Logger.getLogger(StudentTable.class);
    private StudentData[] studentData;
    private String noCourses;
    private String lecturerName;
    private String courseName;
    private String courseProgress;
    private String mark;
    private String buttonInfo;
    private String buttonUnsubscribe;

    public void setStudentData(StudentData[] studentData) {
	this.studentData = studentData;
    }

    public void setNoCourses(String noCourses) {
	this.noCourses = noCourses;
    }

    public void setLecturerName(String lecturerName) {
	this.lecturerName = lecturerName;
    }

    public void setCourseName(String courseName) {
	this.courseName = courseName;
    }

    public void setCourseProgress(String courseProgress) {
	this.courseProgress = courseProgress;
    }

    public void setMark(String mark) {
	this.mark = mark;
    }

    public void setButtonInfo(String buttonInfo) {
	this.buttonInfo = buttonInfo;
    }

    public void setButtonUnsubscribe(String buttonUnsubscribe) {
	this.buttonUnsubscribe = buttonUnsubscribe;
    }

    public int doStartTag() {
	StringBuilder tableBuilder = new StringBuilder();

	if (studentData == null || studentData.length == 0) {
	    tableBuilder.append("<tr><td>" + noCourses + "</td></tr>");
	} else {
	    tableBuilder.append("<table border=\"2\" cellpadding=\"8\">");
	    tableBuilder.append("<tr><td>№</td><td>" + courseName + "</td><td>"
		    + lecturerName + "</td><td>" + courseProgress + "</td><td>"
		    + mark + "</td></tr>");
	    for (int i = 0; i < studentData.length; ++i) {
		tableBuilder.append("<tr><td>" + (i + 1) + "</td>");
		tableBuilder.append("<td>"
			+ studentData[i].getCourse().getName() + "</td>");
		tableBuilder.append("<td>"
			+ studentData[i].getLecturer().getFullName()
			+ "</td>");
		tableBuilder.append("<td>"
			+ studentData[i].getContract().getFinishedPercent()
			+ "</td>");
		tableBuilder
			.append("<td>"
				+ studentData[i].getContract().getMark()
				+ "</td>");
		/*
		 * 
		"<form name=\"LecturerTableForm\" action=\"controller\" "
			+ "method=\"post\"><input type=\"hidden\" name=\"command\" "
			+ "value=\"lecturerStudent\" />"
		 */
		tableBuilder
		    .append("<td><button name=\"ButtonStudentInfo\" value=\""
			    + studentData[i].getContract().getId()
			    + "\">" + buttonInfo + "</button></td>");
		tableBuilder
		    .append("<td><button name=\"ButtonStudentInfo\" value=\""
			    + studentData[i].getContract().getId()
			    + "\">" + buttonUnsubscribe + "</button></td>");
		// tableBuilder.append("</form>");
		tableBuilder.append("</tr>");
	    }
	    tableBuilder.append("</table>");
	}
	try {
	    pageContext.getOut().write(tableBuilder.toString());
	} catch (IOException e) {
	    LOGGER.error("Problem in student table(tag)", e);
	}
	return SKIP_BODY;
    }
}
