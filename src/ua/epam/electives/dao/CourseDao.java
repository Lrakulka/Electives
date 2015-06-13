package ua.epam.electives.dao;

import java.util.List;

import ua.epam.electives.entities.Course;

/**
 * Interface describes method for work with entity {@link Course}.
 * 
 * @author KrabiySok
 * @version 1.0 13/06/15
 */
public interface CourseDao extends CommonDao<Course> {
    /**
     * Method gets list of {@link Course} by key {@link lectureId}.
     * 
     * @param lectureId
     *            key of entity {@link Lecturer} in data hub.
     * @return list of {@link Course}.
     */
    List<Course> getLecturerCourses(Integer lectureId);
}
