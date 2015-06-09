package ua.epam.electives.dao;

import java.util.List;

import ua.epam.electives.entities.Contract;

public interface ContractDao extends CommonDao<Contract> {
    List<Contract> getCourseContracts(Integer courseId);

    List<Contract> getStudentContracts(Integer studentId);
}
