package org.mannayakasha.service.impl;

import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.interfaces.IAccountDao;
import org.mannayakasha.model.Account;
import org.mannayakasha.service.exception.ServiceException;
import org.mannayakasha.service.interfaces.IAccountService;

import java.util.List;

/**
 * Implementation of {@link org.mannayakasha.service.interfaces.IAccountService} interface.
 *
 * @author Pavel
 * @version 1.0 09.08.2017.
 */
public class AccountServiceImpl extends ServiceImpl implements IAccountService {

    private IAccountDao accountDao;

    @Override
    public List<Account> findAll() throws ServiceException {
        try {
            accountDao = factory.createDao(IAccountDao.class);
            return accountDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null; // TODO: 28.08.2017 it's not a good idea to return null
    }

    @Override
    public Account findByLoginAndPassword(String username, String password) throws ServiceException {
        try {
            accountDao = factory.createDao(IAccountDao.class);
            Account account = accountDao.findByLoginAndPassword(username, password);//Hasher.md5(password));
            return account;
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null; // TODO: 29.08.2017 Return null is not good
    }

    @Override
    public Account findByLogin(String login) throws ServiceException {
        return null;
    }

    @Override
    public Account findById(Integer id) throws ServiceException {
        return null;
    }

    @Override
    public void save(Account account) throws ServiceException {

    }

    @Override
    public void delete(Integer id) throws ServiceException {

    }
}
