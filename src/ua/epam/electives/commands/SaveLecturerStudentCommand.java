package ua.epam.electives.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.electives.dao.ContractDao;
import ua.epam.electives.dao.DaoFacadeFactory;
import ua.epam.electives.entities.Contract;

public class SaveLecturerStudentCommand implements Command {
    public static final String COMMAND_TYPE = "saveLecturerStudent";
    public static final String MARK = "mark";
    public static final String COURSE_FINISHED = "courseFinished";
    public static final String COMMENT = "comment";
    public static final String CONTRACT_ID = "contractId";

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	ContractDao contractDao = DaoFacadeFactory.getDaoFactory().getContractDao();
	Integer contractId = Integer.valueOf(request.getParameter(CONTRACT_ID));
	Contract contract = contractDao.getById(contractId);
	Integer mark;
	try {
	    mark = Integer.valueOf(request.getParameter(MARK));
	} catch (NumberFormatException n) {
	    mark = null;
	}
	Short courseProgress;
	try {
	    courseProgress = Short.valueOf(request.getParameter(COURSE_FINISHED));
	} catch (NumberFormatException n) {
	    courseProgress = 0;
	}
	String comment = request.getParameter(COMMENT);	
	contract.setFinishedPercent(courseProgress);
	contract.setMark(mark);
	contract.setComment(comment);
	contractDao.update(contract);
	
	return (new LoginLecturerCommand()).execute(request, response);
    }

    @Override
    public String getCommandType() {
	// TODO Auto-generated method stub
	return COMMAND_TYPE;
    }

}
