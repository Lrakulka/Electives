/**
 * 
 */
package ua.epam.electives.dao;

import ua.epam.electives.entities.Lecturer;

/**
 * Interface describe method for work with entity {@link LecturerDao}
 * 
 * @author KrabiySok
 * @version 1.0 13/06/15
 */
public interface LecturerDao extends CommonDao<Lecturer> {
    /**
     * Returns object of entity {@link Lecturer} by key {@link courseId}.
     * 
     * @param courseId
     *            key of entity {@link Course}.
     * @return object of entity {@link Lecturer} if operation successful or null
     *         if not.
     */
    Lecturer getLecturer(Integer courseId);
}
