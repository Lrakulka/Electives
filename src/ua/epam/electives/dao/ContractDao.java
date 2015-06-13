package ua.epam.electives.dao;

import java.util.List;

import ua.epam.electives.entities.Contract;
import ua.epam.electives.entities.Course;

/**
 * Interface describes methods which works with {@link Contract} entity.
 * 
 * @author KrabiySok
 * @version 1.0 13/06/15
 */
public interface ContractDao extends CommonDao<Contract> {
    /**
     * Returns list of {@link Contract} by field {@link courseId} of entity
     * {@link Course}.
     * 
     * @param courseId
     *            key in data hub of course data
     * @return list of {@link Contract}
     */
    List<Contract> getCourseContracts(Integer courseId);

    /**
     * Returns list of {@link Contract} by field {@link studentId} of entity
     * {@link Student}.
     * 
     * @param studentId
     *            key in data hub of student data
     * @return list of {@link Contract}
     */
    List<Contract> getStudentContracts(Integer studentId);
}
