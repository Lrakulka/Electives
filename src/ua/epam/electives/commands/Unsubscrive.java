package ua.epam.electives.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.electives.dao.ContractDao;
import ua.epam.electives.dao.DaoFacadeFactory;

public class Unsubscrive implements Command {
    public static final String COMMAND_TYPE = "unsubscribe";
    public static final String CONTRACT_ID = "courseId";
    
    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	ContractDao contractDao = DaoFacadeFactory.getDaoFactory().getContractDao();
	Integer contractId = Integer.valueOf(request.getParameter(CONTRACT_ID));
	contractDao.remove(contractId);
	return (new LoginStudentCommand()).execute(request, response);
    }

    @Override
    public String getCommandType() {
	// TODO Auto-generated method stub
	return COMMAND_TYPE;
    }

}
