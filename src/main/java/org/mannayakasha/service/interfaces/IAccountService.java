package org.mannayakasha.service.interfaces;

import org.mannayakasha.model.Account;
import org.mannayakasha.service.IService;
import org.mannayakasha.service.exception.ServiceException;

import java.util.List;

/**
 * Service interface for {@link org.mannayakasha.model.Account}.
 *
 * @author Pavel
 * @version 1.0 28.08.2017.
 */
public interface IAccountService extends IService {
    List<Account> findAll() throws ServiceException;

    Account findByLoginAndPassword(String login, String password) throws ServiceException;

    Account findByLogin(String login) throws ServiceException;

    Account findById(Integer id) throws ServiceException;

    void save(Account account) throws ServiceException;

    void delete(Integer id) throws ServiceException;
}
