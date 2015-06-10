package ua.epam.electives.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.electives.maneger.ConfigurationManager;

public class AuthorizeFailedCommand implements Command {
    public static final String COMMAND_TYPE = "errorLogIn";

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String page = null;
	request.setAttribute(COMMAND_TYPE, new Boolean(true));
	page = ConfigurationManager.getInstance().getProperty(
		ConfigurationManager.LOGIN_FAILED_PATH);
	return page;
    }

    @Override
    public String getCommandType() {
	// TODO Auto-generated method stub
	return COMMAND_TYPE;
    }
}
