package ua.epam.electives.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.electives.dao.DaoFacadeFactory;
import ua.epam.electives.entities.AuthorizedUser;
import ua.epam.electives.entities.Contract;
import ua.epam.electives.entities.StudentData;
import ua.epam.electives.maneger.ConfigurationManager;

public class StartStudentCommand implements Command {
    public static final String COMMAND_TYPE = "loginStudent";
    public static final String STUDENT_DATA = "studentData";
    
    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String page = null;
	DaoFacadeFactory daoFactory = DaoFacadeFactory.getDaoFactory();
	AuthorizedUser authorizedUser = (AuthorizedUser) request.getSession()
		.getAttribute(AuthorizeCommand.COMMAND_TYPE);
	List<Contract> contracts = daoFactory.getContractDao()
		.getStudentContracts(authorizedUser.getId());
	StudentData[] studentData = new StudentData[contracts.size()];
	for (int i = 0; i < studentData.length; ++i) {
	    studentData[i] = new StudentData();
	    studentData[i].setContract(contracts.get(i));
	    studentData[i].setCourse(daoFactory.getCourseDao().getById(
		    contracts.get(i).getIdCourse()));
	    studentData[i].setLecturer(daoFactory.getLecturerDao()
		    .getLecturer(contracts.get(i).getIdCourse()));
	}
	request.setAttribute(STUDENT_DATA, studentData);
	page = ConfigurationManager.getInstance().getProperty(
		ConfigurationManager.STUDENT_MAIN_PAGE_PATH);
	return page;
    }

    @Override
    public String getCommandType() {
	// TODO Auto-generated method stub
	return null;
    }

}
