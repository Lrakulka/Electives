package ua.epam.electives.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.electives.maneger.ConfigurationManager;

/**
 * AuthorizeFailedCommand a command of controller which forms page when
 * authorization of user failed.
 * 
 * @author KrabiySok
 * @version 1.0 13/06/15
 */
public class AuthorizeFailedCommand implements Command {
    /**
     * Describes a type of command as AuthorizeFailedCommand. Value of field {@value #COURSES_DATA}.
     */
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
	return COMMAND_TYPE;
    }
}
