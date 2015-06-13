package ua.epam.electives.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.electives.dao.DaoFactory;
import ua.epam.electives.entities.AuthorizedUser;
import ua.epam.electives.entities.Contract;

/**
 * StudentCourseInfo a command of controller which submit student to course.
 * 
 * @author KrabiySok
 * @version 1.0 13/06/15
 */
public class SubscribeCourseCommand implements Command {
    /**
     * Describes a type of command as SubscribeCourseCommand. Value of field
     * {@value #TYPE_COMMAND}.
     */
    public static final String TYPE_COMMAND = "subscribeCourse";
    /**
     * Describes a parameter value for getting from request student course
     * index. Value of field {@value #COURSE_ID}.
     */
    public static final String COURSE_ID = "courseId";

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	Integer courseId = Integer.valueOf(request.getParameter(COURSE_ID));
	DaoFactory facadeFactory = DaoFactory.getDaoFactory();
	AuthorizedUser user = (AuthorizedUser) request.getSession()
		.getAttribute(AuthorizeCommand.COMMAND_TYPE);
	Contract contract = new Contract(-1, "", null, courseId, user.getId(),
		(short) 0);
	facadeFactory.getContractDao().insert(contract);
	return (new StartStudentCommand()).execute(request, response);
    }

    @Override
    public String getCommandType() {
	// TODO Auto-generated method stub
	return TYPE_COMMAND;
    }

}
