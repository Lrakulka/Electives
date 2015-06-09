/**
 * 
 */
package ua.epam.electives.dao;

import java.util.List;

import ua.epam.electives.entities.Contract;
import ua.epam.electives.entities.Student;

/**
 * @author KrabiySok
 *
 */
public interface StudentDao extends CommonDao<Student> {
    Student getStudent(Integer contractId);

    List<Student> getAllStudent(List<Contract> contracts);
}
