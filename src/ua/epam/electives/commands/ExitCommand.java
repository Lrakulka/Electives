package ua.epam.electives.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.electives.maneger.ConfigurationManager;

public class ExitCommand implements Command {
    public static final String COMMAND_TYPE = "exit";

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	request.getSession().removeAttribute(AuthorizeCommand.COMMAND_TYPE);
	String page = ConfigurationManager.getInstance().getProperty(
		ConfigurationManager.START_PAGE_PATH);
	return page;
    }

    @Override
    public String getCommandType() {
	// TODO Auto-generated method stub
	return COMMAND_TYPE;
    }

}
