/**
 * 
 */
package ua.epam.electives.dao.jdbc;

import org.apache.log4j.NDC;

import ua.epam.electives.dao.LecturerDao;
import ua.epam.electives.entities.Course;
import ua.epam.electives.entities.Lecturer;

/**
 * @author KrabiySok
 *
 */
public class LecturerJdbcDao extends CommonJdbcDao<Lecturer> implements
	LecturerDao {

    public LecturerJdbcDao() {
	super(new Lecturer.LecturerTableInfo());
    }

    @Override
    public Lecturer getLecturer(Integer courseId) {
	Lecturer lecturer = null;
	NDC.push("Get lecturer by course id");
	Course course = (new CourseJdbcDao()).getById(courseId);
	lecturer = getById(course.getId_lecturer());
	NDC.pop();
	return lecturer;
    }
}