package ua.epam.electives.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.electives.maneger.ConfigurationManager;

public class AuthorizeFailedCommand implements Command {
    public static final String AUTHORIZED_USER = "authorizedUser";
    public static final String LOGIN_ERROR = "errorLogIn";

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String page = null;
	request.setAttribute(LOGIN_ERROR, new Boolean(true));
	page = ConfigurationManager.getInstance().getProperty(
		ConfigurationManager.LOGIN_FAILED_PATH);
	return page;
    }

    @Override
    public String getCommandType() {
	// TODO Auto-generated method stub
	return LOGIN_ERROR;
    }
}
