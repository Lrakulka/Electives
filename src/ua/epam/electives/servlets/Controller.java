package ua.epam.electives.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.electives.commands.AuthorizeFailedCommand;
import ua.epam.electives.commands.Command;
import ua.epam.electives.commands.ExitCommand;
import ua.epam.electives.maneger.ConfigurationManager;

public class Controller extends HttpServlet implements Servlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private RequestHelper requestHelper = RequestHelper.getInstance();

    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String page = null;
	Command command = null;
	try {
	    // определение команды, пришедшей из JSP
	    command = requestHelper.getCommand(request);
	    /*
	     * вызов реализованного метода execute() интерфейса Command и
	     * передача параметров классу-обработчику конкретной команды
	     */
	    page = command.execute(request, response);
	    // метод возвращает страницу ответа
	} catch (Exception e) { 
	    e.printStackTrace();
	    // вызов JSP-страницы c cообщением об ошибке
	    page = ConfigurationManager.getInstance().getProperty(
		    ConfigurationManager.ERROR_PAGE_PATH);
	} 
	if ((command != null)
		&& ((command.getCommandType() == ExitCommand.COMMAND_TYPE) || (command
			.getCommandType() == AuthorizeFailedCommand.LOGIN_ERROR))) {
	    response.sendRedirect(page);
	} else {
	    RequestDispatcher dispatcher = getServletContext()
		    .getRequestDispatcher(page);
	    dispatcher.forward(request, response);
	}
    }
}
