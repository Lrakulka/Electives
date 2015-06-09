package ua.epam.electives.dao.jdbc;

import org.apache.log4j.Logger;

import ua.epam.electives.dao.AuthorizeUserDao;
import ua.epam.electives.dao.ContractDao;
import ua.epam.electives.dao.CourseDao;
import ua.epam.electives.dao.DaoFacadeFactory;
import ua.epam.electives.dao.LecturerDao;
import ua.epam.electives.dao.StudentDao;

public class DaoJdbcFacade extends DaoFacadeFactory {
    private static final Logger LOGGER = Logger.getLogger(DaoJdbcFacade.class);

    @Override
    public LecturerDao getLecturerDao() {
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Get lecturer DAO");
	}
	return LecturerJdbcDaoSingleton.getLectureJdbcDao();
    }

    @Override
    public StudentDao getStudentDao() {
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Get student DAO");
	}
	return StudentJdbcDaoSingleton.getStudentJdbcDao();
    }

    @Override
    public CourseDao getCourseDao() {
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Get course DAO");
	}
	return CourseJdbcDaoSingleton.getCourseJdbcDao();
    }

    @Override
    public ContractDao getContractDao() {
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Get contract DAO");
	}
	return ContractJdbcDaoSingleton.getContractJdbcDao();
    }

    @Override
    public AuthorizeUserDao getAuthorizeUser() {
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Get authorize user DAO");
	}
	return AuthorizeUserJdbcDao.getAuthorizeUserJdbcDao();
    }
}