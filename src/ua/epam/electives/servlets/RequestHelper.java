package ua.epam.electives.servlets;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import ua.epam.electives.commands.AuthorizeFailedCommand;
import ua.epam.electives.commands.Command;
import ua.epam.electives.commands.ExitCommand;
import ua.epam.electives.commands.LecturerStudentCommand;
import ua.epam.electives.commands.LoginLecturerCommand;
import ua.epam.electives.commands.LoginStudentCommand;
import ua.epam.electives.commands.NoCommand;
import ua.epam.electives.commands.SaveLecturerStudentCommand;
import ua.epam.electives.dao.DaoFacadeFactory;
import ua.epam.electives.entities.AuthorizedUser;

public class RequestHelper {
    private static final String COMMAND = "command";
    private static final String PARAM_NAME_LOGIN = "userName";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String LOGIN = "login";
    public static final String AUTHORIZED_USER = "authorizedUser";
    private static RequestHelper instance = null;
    private static Command authorizeFailed;
    private static Command exitCommand;
    HashMap<String, Command> commands = new HashMap<String, Command>();
    HashMap<String, Command> studentCommands = new HashMap<String, Command>();
    HashMap<String, Command> lecturerCommands = new HashMap<String, Command>();

    private RequestHelper() {
	// заполнение таблиц командами
	lecturerCommands.put(LOGIN, new LoginLecturerCommand());
	lecturerCommands.put(LecturerStudentCommand.TYPE_COMMAND,
		new LecturerStudentCommand());
	lecturerCommands.put(SaveLecturerStudentCommand.COMMAND_TYPE,
		new SaveLecturerStudentCommand());
	studentCommands.put(LOGIN, new LoginStudentCommand());
	exitCommand = new ExitCommand();
	commands.put("exit", exitCommand);

	authorizeFailed = new AuthorizeFailedCommand();
    }

    public Command getCommand(HttpServletRequest request) {
	// извлечение команды из запроса
	String action = request.getParameter(COMMAND);
	AuthorizedUser authorizedUser = (AuthorizedUser) request.getSession()
		.getAttribute(AuthorizeFailedCommand.AUTHORIZED_USER);
	Command command = null;
	if ((authorizedUser == null) && (LOGIN.equals(action))) {
	    authorizedUser = authorize(request);
	    if (authorizedUser == null) {
		return authorizeFailed;
	    }
	}
	if (authorizedUser != null) {
	    // получение объекта, соответствующего команде
	    command = commands.get(action);
	    if (command == null) {
		if (authorizedUser.isLecturer()) {
		    command = lecturerCommands.get(action);
		} else {
		    command = studentCommands.get(action);
		}
	    }
	} else {
	    command = exitCommand;
	}
	if (command == null) {
	    // если команды не существует в текущем объекте
	    command = new NoCommand();
	}
	return command;
    }

    // создание единственного объекта по шаблону Singleton
    public static RequestHelper getInstance() {
	if (instance == null) {
	    instance = new RequestHelper();
	}
	return instance;
    }

    private AuthorizedUser authorize(HttpServletRequest request) {
	String login = request.getParameter(PARAM_NAME_LOGIN);
	String pass = request.getParameter(PARAM_NAME_PASSWORD);
	// проверка логина и пароля
	DaoFacadeFactory daoFactory = DaoFacadeFactory.getDaoFactory();
	AuthorizedUser authorizedUser = daoFactory.getAuthorizeUser()
		.authorize(login, pass);
	if (authorizedUser != null) {
	    request.getSession().setAttribute(AUTHORIZED_USER, authorizedUser);
	}
	return authorizedUser;
    }
}
