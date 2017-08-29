package org.mannayakasha.dao;

import org.mannayakasha.dao.pool.ConnectionPool;
import org.mannayakasha.dao.pool.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is for working with database.
 *
 * @author Pavel
 * @version 1.0 28.08.2017.
 */
public class DBConnector {

    /** Connection pool instance */
    private ConnectionPool pool = ConnectionPool.getInstance();

    /** Class fields */
    private Connection connection;

    protected Statement statement;

    protected PreparedStatement preparedStatement;

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = pool.getConnection(); // get connection from connection pool
            } catch (ConnectionPoolException e) {
                //LOGGER.error("It's impossible to take connection from connection pool. " + e);
            }
        }
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                //connection.close(); // TODO: 06.08.2017 Is it work?
                //PooledConnection pooledConnection = new PooledConnection(connection);
                //pool.closeConnection(pooledConnection); // close connection and return it into connection pool
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                /*catch (SQLException e) {
                LOGGER.error("It's impossible to close connection. " + e);
            }*/
            }
        }
    }

    public Statement getStatement() {//(Connection connection) {
        if (statement == null) {
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                //LOGGER.error(ResourceManager.getProperty(MSG_CREATE_STATEMENT_ERROR), e); // TODO: 04.08.2017 Write log message
            }
        }
        return statement;
    }

    public void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                //LOGGER.error("It's impossible to close statement. " + e);
            }
        }
    }

    public PreparedStatement getPreparedStatement(String query) {
        if (preparedStatement == null) {
            try {
                preparedStatement = connection.prepareStatement(query);
            } catch (SQLException e) {
                //LOGGER.error(ResourceManager.getProperty(MSG_CREATE_PREPARED_ERROR), e); // TODO: 04.08.2017 Write log message
            }
        }
        return preparedStatement;
    }

    public PreparedStatement getPreparedStatement(String query, int key) {
        if (preparedStatement == null) {
            try {
                preparedStatement = connection.prepareStatement(query, key);
            } catch (SQLException e) {
                //LOGGER.error(ResourceManager.getProperty(MSG_CREATE_PREPARED_ERROR), e); // TODO: 04.08.2017 Write log message
            }
        }
        return preparedStatement;
    }

    public void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                //LOGGER.error("It's impossible to close prepared statement. " + e);
            }
        }
    }
}
