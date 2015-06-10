package ua.epam.electives.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.epam.electives.dao.CourseDao;
import ua.epam.electives.entities.Course;

public class CourseJdbcDao extends CommonJdbcDao<Course> implements
	CourseDao {
    private static final Logger LOGGER = Logger
	    .getLogger(CourseJdbcDao.class);
    private final Course.CourseTableInfo courseTableInfo;

    public CourseJdbcDao() {
	courseTableInfo = new Course.CourseTableInfo();
	super.setTableInfo(courseTableInfo);
    }

    @Override
    public List<Course> getLecturerCourses(Integer lectureId) {
	List<Course> list = new ArrayList<Course>();
	Connection conn = DaoJdbcConnection.getConnection();
	try (Statement query = (Statement) conn.createStatement()) {
	    ResultSet rs = query.executeQuery((new StringBuilder(
		    "SELECT * FROM ").append(courseTableInfo.getTableName())
		    .append(" WHERE ").append(courseTableInfo.getTableName())
		    .append(".")
		    .append(courseTableInfo.getTableFieldsName()[2])
		    .append("=").append(String.valueOf(lectureId))).toString());
	    while (rs.next()) {
		list.add(new Course(rs.getInt(1), rs.getString(2), rs.getInt(3)));
	    }
	} catch (SQLException e) {
	    LOGGER.error(
		    "Get lecturer courses coureseId="
			    + String.valueOf(lectureId), e);
	} finally {
            if (conn!=null) {
        	try {
        	    conn.close();
    	    	} catch (Exception ignore) {}
            }
	}
	if (LOGGER.isDebugEnabled()) {
	    LOGGER.debug("Get lecturer courses size="
		    + String.valueOf(list.size()) + "  studentId="
		    + String.valueOf(lectureId));
	}
	return list;
    }
}