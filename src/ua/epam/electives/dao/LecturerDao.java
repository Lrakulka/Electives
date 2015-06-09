/**
 * 
 */
package ua.epam.electives.dao;

import ua.epam.electives.entities.Lecturer;

/**
 * @author KrabiySok
 * 
 */
public interface LecturerDao extends CommonDao<Lecturer> {
    Lecturer getLecturer(Integer courseId);
}
