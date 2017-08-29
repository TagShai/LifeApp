package org.mannayakasha.dao.impl.mysql;

import org.mannayakasha.dao.BaseDao;
import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.interfaces.IAccountDao;
import org.mannayakasha.model.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link IAccountDao} interface.
 *
 * @author Pavel
 * @version 1.0 28.08.2017.
 */
public class AccountDaoImpl extends BaseDao<Account> implements IAccountDao {

    // TODO: 28.08.2017 Add logger

    @Override
    public List<Account> findAll() throws DaoException {
        String sql = "SELECT * FROM `accounts`";

        try (PreparedStatement preparedStatement = connector.getPreparedStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Account> users = new ArrayList<>();

            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                users.add(account);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e); // // TODO: 28.08.2017 Add logger
        }
    }

/*    @Override
    public List<Account> findAll() throws DaoException {
        String sql = "SELECT * FROM `accounts`";

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connector.getStatement();
            resultSet = statement.executeQuery(sql);

            List<Account> users = new ArrayList<>();

            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                users.add(account);
            }
            return users;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connector.closeStatement(statement);
        }
    }*/

    @Override
    public Account findByLoginAndPassword(String username, String password) throws DaoException {
        String sql = "SELECT `id`, `accessType` FROM `accounts` WHERE `username` = ? AND `password` = ?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connector.getPreparedStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            Account account = null;
            if(resultSet.next()) {
                account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setUsername(username);
                account.setPassword(password);
                account.setAccessType(resultSet.getString("accessType"));
            }
            return account;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                // TODO: 11.08.2017 Empty catch
            }
            try {
                connector.closePreparedStatement(preparedStatement);
            } catch(NullPointerException e) {
                // TODO: 11.08.2017 Empty catch
            }
        }
    }

    @Override
    public Account findByLogin(String username) throws DaoException {
        return null;
    }

    @Override
    public Integer create(Account entity) throws DaoException {
        return null;
    }

    @Override
    public void delete(int id) throws DaoException {

    }

    @Override
    public void update(Account entity) throws DaoException {

    }

    @Override
    public Account findById(int id) throws DaoException {
        return null;
    }
}
