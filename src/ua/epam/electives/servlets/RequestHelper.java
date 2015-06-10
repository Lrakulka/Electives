package ua.epam.electives.servlets;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import ua.epam.electives.commands.AllCoursesCommand;
import ua.epam.electives.commands.AuthorizeCommand;
import ua.epam.electives.commands.AuthorizeFailedCommand;
import ua.epam.electives.commands.Command;
import ua.epam.electives.commands.ExitCommand;
import ua.epam.electives.commands.LecturerStudentCommand;
import ua.epam.electives.commands.StartLecturerCommand;
import ua.epam.electives.commands.NoCommand;
import ua.epam.electives.commands.SaveLecturerStudentCommand;
import ua.epam.electives.commands.StartStudentCommand;
import ua.epam.electives.commands.StudentCourseInfo;
import ua.epam.electives.commands.SubscribeCourseCommand;
import ua.epam.electives.commands.Unsubscrive;
import ua.epam.electives.entities.AuthorizedUser;

public class RequestHelper {
    public static final String COMMAND = "command";
    private static RequestHelper instance = null;
    private static Command exitCommand;
    HashMap<String, Command> commands = new HashMap<String, Command>();
    HashMap<String, Command> studentCommands = new HashMap<String, Command>();
    HashMap<String, Command> lecturerCommands = new HashMap<String, Command>();

    private RequestHelper() {
	// заполнение таблиц командами
	lecturerCommands.put(StartLecturerCommand.COMMAND_TYPE, new StartLecturerCommand());
	lecturerCommands.put(LecturerStudentCommand.TYPE_COMMAND,
		new LecturerStudentCommand());
	lecturerCommands.put(SaveLecturerStudentCommand.COMMAND_TYPE,
		new SaveLecturerStudentCommand());
	studentCommands.put(StartStudentCommand.COMMAND_TYPE, new StartStudentCommand());
	studentCommands.put(Unsubscrive.COMMAND_TYPE, new Unsubscrive());
	studentCommands.put(StudentCourseInfo.COMMAND_TYPE,
		new StudentCourseInfo());
	studentCommands.put(SubscribeCourseCommand.TYPE_COMMAND,
		new SubscribeCourseCommand());
	studentCommands.put(AllCoursesCommand.COMMAND_TYPE,
		new AllCoursesCommand());
	exitCommand = new ExitCommand();
	commands.put(ExitCommand.COMMAND_TYPE, exitCommand);
	commands.put(AuthorizeCommand.COMMAND_TYPE, new AuthorizeCommand());
	commands.put(AuthorizeFailedCommand.COMMAND_TYPE, new AuthorizeFailedCommand());
    }

    public Command getCommand(HttpServletRequest request) {
	// извлечение команды из запроса
	String action = (String) request.getAttribute(COMMAND);
	if (action == null) {
	    action = request.getParameter(COMMAND);
	}
	Command command = commands.get(action);
	if (command != null) {
	    return command;
	}
	AuthorizedUser authorizedUser = (AuthorizedUser) request.getSession()
		.getAttribute(AuthorizeCommand.COMMAND_TYPE);
	if (authorizedUser != null) {
	    if (authorizedUser.isLecturer()) {
		command = lecturerCommands.get(action);
	    } else {
		command = studentCommands.get(action);
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
}
