package ua.epam.electives.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import org.apache.log4j.Logger;

import ua.epam.electives.dao.AuthorizeUserDao;
import ua.epam.electives.entities.AuthorizedUser;
import ua.epam.electives.entities.Lecturer;
import ua.epam.electives.entities.Student;

public class AuthorizeUserJdbcDao implements AuthorizeUserDao {
    private static final Logger LOGGER = Logger
	    .getLogger(ContractJdbcDaoSingleton.class);
    private static AuthorizeUserDao authorizeUser = new AuthorizeUserJdbcDao();
    private Lecturer.LecturerTableInfo lecturerTableInfo = new Lecturer.LecturerTableInfo();
    private Student.StudentTableInfo studentTableInfo = new Student.StudentTableInfo();

    private AuthorizeUserJdbcDao() {
    }

    public static AuthorizeUserDao getAuthorizeUserJdbcDao() {
	return authorizeUser;
    }

    @Override
    public AuthorizedUser authorize(String name, String pwd) {
	AuthorizedUser authorizedUser = null;
	try {
	    authorizedUser = authorizeUser(name, pwd,
		    lecturerTableInfo.getTableName());
	    if (authorizedUser != null) {
		authorizedUser.setLecturer(true);
	    } else {
		authorizedUser = authorizeUser(name, pwd,
			studentTableInfo.getTableName());
		if (authorizedUser != null) {
		    authorizedUser.setLecturer(false);
		}
	    }
	} catch (Exception e1) {
	    // I catch exception in my log in method authorizeUser(..)
	}
	return authorizedUser;
    }

    private AuthorizedUser authorizeUser(String name, String pwd,
	    String tableName) throws Exception {
	AuthorizedUser authorizedUser = new AuthorizedUser(name, pwd);
	Connection conn = DaoJdbcConnection.getConnection();
	try (Statement query = (Statement) conn.createStatement()) {
	    ResultSet rs = query.executeQuery("SELECT id FROM " + tableName
		    + " WHERE (" + lecturerTableInfo.getTableFieldsName()[1]
		    + "='" + authorizedUser.getFullName() + "' AND "
		    + lecturerTableInfo.getTableFieldsName()[2] + "='"
		    + authorizedUser.getPwd() + "')");
	    if (rs.next()) {
		authorizedUser.setId(rs.getInt(1));
	    } else {
		authorizedUser = null;
	    }
	    if (rs.next()) {
		LOGGER.error("Authorized user repeats name=" + name);
		throw new Exception();
	    }
	} catch (SQLException e) {
	    LOGGER.error("Authorized user name=" + name, e);
	} finally {
            if (conn!=null) {
        	try {
        	    conn.close();
    	    	} catch (Exception ignore) {}
            }
	}
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Authorized user name=" + name);
	}
	return authorizedUser;
    }
}
