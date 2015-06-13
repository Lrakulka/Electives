package ua.epam.electives.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.electives.dao.ContractDao;
import ua.epam.electives.dao.DaoFactory;
import ua.epam.electives.entities.Contract;

/**
 * SaveLecturerStudentCommand a command of controller which save data of student
 * course to database.
 * 
 * @author KrabiySok
 * @version 1.0 13/06/15
 */
public class SaveLecturerStudentCommand implements Command {
    /**
     * Describes a type of command as SaveLecturerStudentCommand. Value of field
     * {@value #COURSES_DATA}.
     */
    public static final String COMMAND_TYPE = "saveLecturerStudent";
    /**
     * Describes a parameter value for getting from request student mark. Value
     * of field {@value #COURSES_DATA}.
     */
    public static final String MARK = "mark";
    /**
     * Describes a parameter value for getting from request student course
     * finished process. Value of field {@value #COURSE_FINISHED}.
     */
    public static final String COURSE_FINISHED = "courseFinished";
    /**
     * Describes a parameter value for getting from request lecturer comment for
     * student. Value of field {@value #COMMENT}.
     */
    public static final String COMMENT = "comment";
    /**
     * Describes a parameter value for getting from request student contract
     * index in database. Value of field {@value #CONTRACT_ID}.
     */
    public static final String CONTRACT_ID = "contractId";

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	ContractDao contractDao = DaoFactory.getDaoFactory().getContractDao();
	Integer contractId = Integer.valueOf(request.getParameter(CONTRACT_ID));
	Contract contract = contractDao.getById(contractId);
	Integer mark;
	try {
	    mark = Integer.valueOf(request.getParameter(MARK));
	} catch (NumberFormatException n) {
	    mark = null;
	}
	Integer courseProgress;
	try {
	    courseProgress = Integer.valueOf(request
		    .getParameter(COURSE_FINISHED));
	} catch (NumberFormatException n) {
	    courseProgress = 0;
	}
	String comment = request.getParameter(COMMENT);
	contract.setFinishedPercent(courseProgress);
	contract.setMark(mark);
	contract.setComment(comment);
	contractDao.update(contract);

	return (new StartLecturerCommand()).execute(request, response);
    }

    @Override
    public String getCommandType() {
	return COMMAND_TYPE;
    }

}
