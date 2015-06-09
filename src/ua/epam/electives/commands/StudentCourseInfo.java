package ua.epam.electives.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.electives.dao.DaoFacadeFactory;
import ua.epam.electives.entities.Contract;
import ua.epam.electives.entities.Course;
import ua.epam.electives.maneger.ConfigurationManager;

public class StudentCourseInfo implements Command {
    public static final String COMMAND_TYPE = "courseInfo";
    public static final String CONTRACT_ID = "courseId";
    public static final String COURSE_DATA = "courseData";
    public static final String CONTRACT_DATA = "contractData";
    
    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String page;
	Integer contractId = Integer.valueOf(request.getParameter(CONTRACT_ID));
	DaoFacadeFactory facadeFactory = DaoFacadeFactory.getDaoFactory();
	Contract contract = facadeFactory.getContractDao().getById(contractId);
	Course course = facadeFactory.getCourseDao().getById(contract.getIdCourse());
	request.setAttribute(CONTRACT_DATA, contract);
	request.setAttribute(COURSE_DATA, course);
	page = ConfigurationManager.getInstance().getProperty(
		ConfigurationManager.STUDENT_COURSE_PAGE_PATH);
	return page;
    }

    @Override
    public String getCommandType() {
	// TODO Auto-generated method stub
	return COMMAND_TYPE;
    }

}
