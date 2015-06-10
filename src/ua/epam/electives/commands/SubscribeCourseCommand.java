package ua.epam.electives.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.electives.dao.DaoFactory;
import ua.epam.electives.entities.AuthorizedUser;
import ua.epam.electives.entities.Contract;

public class SubscribeCourseCommand implements Command {
    public static final String TYPE_COMMAND = "subscribeCourse";
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
