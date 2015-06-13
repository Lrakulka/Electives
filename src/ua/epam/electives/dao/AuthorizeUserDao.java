package ua.epam.electives.dao;

import ua.epam.electives.entities.AuthorizedUser;

/**
 * Interface represented method for work with Entity {@link AuthorizedUser}.
 * 
 * @author KrabiySok
 * @version 1.0 13/06/15
 */
public interface AuthorizeUserDao {
    /**
     * Method makes user authorization using data hub.
     * @param name user login
     * @param pwd user password
     * @return AuthorizedUser object is successful or null if not
     */
    abstract AuthorizedUser authorize(String name, String pwd);
}
