package ua.epam.electives.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.electives.dao.DaoFacadeFactory;
import ua.epam.electives.entities.Contract;
import ua.epam.electives.entities.Student;
import ua.epam.electives.maneger.ConfigurationManager;

public class LecturerStudentCommand implements Command {
    public static final String TYPE_COMMAND = "lecturerStudent";
    public static final String CONTRACT_DATA = "contractData";
    public static final String STUDENT_DATA = "studentData";
    
    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String page;
	Integer contractId = Integer.valueOf(request.getParameter("ButtonStudentInfo"));
	DaoFacadeFactory facadeFactory = DaoFacadeFactory.getDaoFactory();
	Contract contract = facadeFactory.getContractDao().getById(contractId);
	Student student = facadeFactory.getStudentDao().getById(contract.getIdStudent());
	request.setAttribute(CONTRACT_DATA, contract);
	request.setAttribute(STUDENT_DATA, student);
	page = ConfigurationManager.getInstance().getProperty(
		ConfigurationManager.LECTURER_STUDENT_PAGE_PATH);
	return page;
    }

    @Override
    public String getCommandType() {
	return TYPE_COMMAND;
    }

}
