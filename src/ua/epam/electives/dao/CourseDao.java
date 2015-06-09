package ua.epam.electives.dao;

import java.util.List;

import ua.epam.electives.entities.Course;

public interface CourseDao extends CommonDao<Course> {
    List<Course> getLecturerCourses(Integer lectureId);
}
