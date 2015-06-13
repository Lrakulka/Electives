package ua.epam.electives.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.electives.dao.DaoFactory;
import ua.epam.electives.entities.AuthorizedUser;
import ua.epam.electives.entities.Course;
import ua.epam.electives.entities.LecturerData;
import ua.epam.electives.maneger.ConfigurationManager;

/**
 * StartLecturerCommand a command of controller which forms beginning lecturer
 * page.
 * 
 * @author KrabiySok
 * @version 1.0 13/06/15
 */
public class StartLecturerCommand implements Command {
    /**
     * Describes a type of command as StartLecturerCommand. Value of field
     * {@value #COMMAND_TYPE}.
     */
    public static final String COMMAND_TYPE = "loginLecturer";
    /**
     * Describes a parameter value for setting to request lecturer courses data.
     * Value of field {@value #LECTURER_DATA}.
     */
    public static final String LECTURER_DATA = "lecturerData";

    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String page = null;
	DaoFactory daoFactory = DaoFactory.getDaoFactory();
	AuthorizedUser authorizedUser = (AuthorizedUser) request.getSession()
		.getAttribute(AuthorizeCommand.COMMAND_TYPE);
	List<Course> courses = daoFactory.getCourseDao().getLecturerCourses(
		authorizedUser.getId());
	LecturerData[] lecturerData = new LecturerData[courses.size()];
	for (int i = 0; i < courses.size(); ++i) {
	    lecturerData[i] = new LecturerData();
	    lecturerData[i].setCourse(courses.get(i));
	    lecturerData[i].setContracts(daoFactory.getContractDao()
		    .getCourseContracts(courses.get(i).getId()));
	    lecturerData[i].setStudents(daoFactory.getStudentDao()
		    .getAllStudent(lecturerData[i].getContracts()));
	}
	request.setAttribute(LECTURER_DATA, lecturerData);
	page = ConfigurationManager.getInstance().getProperty(
		ConfigurationManager.LECTURER_MAIN_PAGE_PATH);

	return page;
    }

    @Override
    public String getCommandType() {
	return COMMAND_TYPE;
    }
}