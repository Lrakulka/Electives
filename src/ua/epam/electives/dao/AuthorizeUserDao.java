package ua.epam.electives.dao;

import ua.epam.electives.entities.AuthorizedUser;

public interface AuthorizeUserDao {
    abstract AuthorizedUser authorize(String name, String pwd);
}
