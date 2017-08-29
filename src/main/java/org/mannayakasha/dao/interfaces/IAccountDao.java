package org.mannayakasha.dao.interfaces;

import org.mannayakasha.dao.IDao;
import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.model.Account;

import java.util.List;

/**
 * Dao interface for {@link org.mannayakasha.model.Account}.
 *
 * @author Pavel
 * @version 1.0 28.08.2017.
 */
public interface IAccountDao extends IDao<Account> {
    List<Account> findAll() throws DaoException;

    Account findByLoginAndPassword(String username, String password) throws DaoException;

    Account findByLogin(String username) throws DaoException;
}
