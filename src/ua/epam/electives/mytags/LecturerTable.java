package ua.epam.electives.mytags;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ua.epam.electives.entities.LecturerData;

public class LecturerTable extends TagSupport {
    /**
     * 
     */
    private static final long serialVersionUID = 8043435102796555074L;
    private static final Logger LOGGER = Logger.getLogger(LecturerTable.class);
    private String noStudent;
    private String studentName;
    private String mark;
    private String studyProgress;
    private String buttonInfo;
    private LecturerData[] lecturerData;

    public void setMark(String mark) {
	this.mark = mark;
    }

    public void setStudyProgress(String studyProgress) {
	this.studyProgress = studyProgress;
    }

    public void setButtonInfo(String buttonInfo) {
	this.buttonInfo = buttonInfo;
    }

    public void setStudentName(String studentName) {
	this.studentName = studentName;
    }

    public void setNoStudent(String noStudent) {
	this.noStudent = noStudent;
    }

    public void setLecturerData(LecturerData[] lecturerData) {
	this.lecturerData = lecturerData;
    }

    public int doStartTag() {
	StringBuilder tableBuilder = new StringBuilder(
		"<form name=\"LecturerTableForm\" action=\"controller\" "
			+ "method=\"post\"><input type=\"hidden\" name=\"command\" "
			+ "value=\"lecturerStudent\" /><table border=\"2\" cellpadding=\"8\">");
	for (LecturerData lData : lecturerData) {
	    tableBuilder.append("<tr><td>" + lData.getCourse().getName()
		    + "</td></tr>");
	    if (lData.getContracts().isEmpty()) {
		tableBuilder.append("<tr><td>" + noStudent + "</td></tr>");
	    } else {
		tableBuilder.append("<tr><td>№</td><td>" + studentName
			+ "</td><td>" + studyProgress + "</td><td>" + mark
			+ "</td></tr>");
		for (int i = 0; i < lData.getContracts().size(); ++i) {
		    tableBuilder.append("<tr><td>" + i + 1 + "</td><td>"
			    + lData.getStudents().get(i).getFullName()
			    + "</td><td>"
			    + lData.getContracts().get(i).getFinishedPercent()
			    + "%</td>");
		    if (lData.getContracts().get(i).isFinished()) {
			tableBuilder.append("<td>"
				+ lData.getContracts().get(i).getMark()
				+ "</td>");
		    } else {
			tableBuilder.append("<td>---</td>");
		    }
		    tableBuilder
			    .append("<td><button name=\"ButtonStudentInfo\" value=\""
				    + lData.getContracts().get(i).getId()
				    + "\">" + buttonInfo + "</button></td>");
		    tableBuilder.append("</tr>");
		}
	    }
	}
	tableBuilder.append("</table></form>");
	try {
	    pageContext.getOut().write(tableBuilder.toString());
	} catch (IOException e) {
	    LOGGER.error("Problem in lecture table(tag)", e);
	}
	return SKIP_BODY;
    }
}