package ua.epam.electives.dao;

import org.apache.log4j.Logger;

import ua.epam.electives.dao.jdbc.DaoJdbcFacade;

public abstract class DaoFacadeFactory {
    private static final Logger LOGGER = Logger
	    .getLogger(DaoFacadeFactory.class);
    private static DaoFacadeFactory currDaoFactory;

    /*
     * Gives access to lecture data
     * 
     * @return DAO of lecturer
     */
    public abstract LecturerDao getLecturerDao();

    /*
     * Gives access to student data
     * 
     * @return DAO of student
     */
    public abstract StudentDao getStudentDao();

    /*
     * Gives access to course data
     * 
     * @return DAO of course
     */
    public abstract CourseDao getCourseDao();

    /*
     * Gives access to contract data
     * 
     * @return DAO of contract
     */
    public abstract ContractDao getContractDao();

    /*
     * Gives access to Authorize user data
     * 
     * @return DAO of contract
     */
    public abstract AuthorizeUserDao getAuthorizeUser();

    /**
     * Gives access to DAOs
     * 
     * @return DAO Factory object
     */
    public static DaoFacadeFactory getDaoFactory() {
	if (currDaoFactory == null) {
	    currDaoFactory = new DaoJdbcFacade();
	}
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("DaoJdbcFactory created");
	}
	return currDaoFactory;
    }
}
