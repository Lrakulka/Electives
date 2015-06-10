package ua.epam.electives.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.electives.dao.DaoFacadeFactory;
import ua.epam.electives.entities.AuthorizedUser;
import ua.epam.electives.servlets.RequestHelper;

public class AuthorizeCommand implements Command {
    public static final String COMMAND_TYPE = "authorizedUser";
    private static final String PARAM_NAME_LOGIN = "userName";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String LOOP_PROTECTION = "loopProtection";

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String login = request.getParameter(PARAM_NAME_LOGIN);
	String pass = request.getParameter(PARAM_NAME_PASSWORD);
	// проверка логина и пароля
	DaoFacadeFactory daoFactory = DaoFacadeFactory.getDaoFactory();
	AuthorizedUser authorizedUser = daoFactory.getAuthorizeUser()
		.authorize(login, pass);
	request.removeAttribute(PARAM_NAME_LOGIN);
	request.removeAttribute(PARAM_NAME_PASSWORD);
	if (request.getAttribute(LOOP_PROTECTION) == null) {
	    request.setAttribute(LOOP_PROTECTION, new Boolean(true));
	} else {
	    return null;
	}
	if (authorizedUser != null) {
	    request.getSession().setAttribute(COMMAND_TYPE, authorizedUser);
	    if (authorizedUser.isLecturer()) {
		request.setAttribute(RequestHelper.COMMAND,
			StartLecturerCommand.COMMAND_TYPE);
	    } else {
		request.setAttribute(RequestHelper.COMMAND,
			StartStudentCommand.COMMAND_TYPE);
	    }
	} else {
	    request.setAttribute(RequestHelper.COMMAND,
		    AuthorizeFailedCommand.COMMAND_TYPE);
	}
	return RequestHelper.getInstance().getCommand(request)
		.execute(request, response);
    }

    @Override
    public String getCommandType() {
	// TODO Auto-generated method stub
	return COMMAND_TYPE;
    }

}
