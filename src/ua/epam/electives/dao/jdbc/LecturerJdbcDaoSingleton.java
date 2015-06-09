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
public class LecturerJdbcDaoSingleton extends CommonJdbcDao<Lecturer> implements
	LecturerDao {
    private static LecturerDao lecturerDao = new LecturerJdbcDaoSingleton();

    private LecturerJdbcDaoSingleton() {
	super(new Lecturer.LecturerTableInfo());
    }

    public static LecturerDao getLectureJdbcDao() {
	return lecturerDao;
    }

    @Override
    public Lecturer getLecturer(Integer courseId) {
	Lecturer lecturer = null;
	NDC.push("Get lecturer by course id");
	Course course = CourseJdbcDaoSingleton.getCourseJdbcDao().getById(
		courseId);
	lecturer = getById(course.getId_lecturer());
	NDC.pop();
	return lecturer;
    }
}