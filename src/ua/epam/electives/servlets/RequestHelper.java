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

/**
 * Helps controller get work with users commands.
 * 
 * @author KrabiySok
 * @version 1.0 13/06/15
 */
public class RequestHelper {
    /**
     * Describes parameter value for getting type of command. {@value #COMMAND}.
     */
    public static final String COMMAND = "command";
    private static RequestHelper instance = null;
    private static Command exitCommand;
    /**
     * Basics commands for lecturer and student.
     */
    HashMap<String, Command> commands = new HashMap<String, Command>();
    /**
     * Commands for student.
     */
    HashMap<String, Command> studentCommands = new HashMap<String, Command>();
    /**
     * Commands for lecturer.
     */
    HashMap<String, Command> lecturerCommands = new HashMap<String, Command>();

    /**
     * Constructor initialization all commands.
     */
    private RequestHelper() {
	lecturerCommands.put(StartLecturerCommand.COMMAND_TYPE,
		new StartLecturerCommand());
	lecturerCommands.put(LecturerStudentCommand.TYPE_COMMAND,
		new LecturerStudentCommand());
	lecturerCommands.put(SaveLecturerStudentCommand.COMMAND_TYPE,
		new SaveLecturerStudentCommand());
	studentCommands.put(StartStudentCommand.COMMAND_TYPE,
		new StartStudentCommand());
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
	commands.put(AuthorizeFailedCommand.COMMAND_TYPE,
		new AuthorizeFailedCommand());
    }

    /**
     * Gets command object.
     * 
     * @param request
     *            the HttpServletRequest object that contains the client's
     *            request
     * @return command.
     */
    public Command getCommand(HttpServletRequest request) {
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
	    command = new NoCommand();
	}
	return command;
    }

    /**
     * Gets singleton of {@link RequestHelper}.
     * 
     * @return singleton.
     */
    public static RequestHelper getInstance() {
	if (instance == null) {
	    instance = new RequestHelper();
	}
	return instance;
    }
}
