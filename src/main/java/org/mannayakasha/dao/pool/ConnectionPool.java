package org.mannayakasha.dao.pool;

import org.mannayakasha.dao.pool.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Contains realization of the connection pool.
 *
 * @author Pavel
 * @version 1.0 28.08.2017.
 */
public final class ConnectionPool {
    //private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private String url;
    private String user;
    private String password;
    private int minSize;
    private int maxSize;
    private int validateConnectionTimeout;

    private PoolConfiguration poolConfiguration;

    private BlockingQueue<PooledConnection> freeConnections = new LinkedBlockingQueue<>();
    private Set<PooledConnection> usedConnections = new ConcurrentSkipListSet<>();

    private ConnectionPool() {}

    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }

    public synchronized void init() throws ConnectionPoolException {
        try {
            destroy();
            poolConfiguration = new PoolConfiguration();
            Class.forName(poolConfiguration.getDriver());
            this.url = poolConfiguration.getUrl();
            this.user = poolConfiguration.getUser();
            this.password = poolConfiguration.getPassword();
            this.minSize = poolConfiguration.getMinSize();
            this.maxSize = poolConfiguration.getMaxSize();
            this.validateConnectionTimeout = poolConfiguration.getTimeout();
            for (int counter = 0; counter < minSize; counter++) {
                freeConnections.put(createConnection());
            }
        } catch (ClassNotFoundException | SQLException | InterruptedException e) {
            //LOGGER.error("It is impossible to initialize connection pool", e);
            e.printStackTrace();
            throw new ConnectionPoolException(e.getMessage(), e.getCause());
        }
    }

    public synchronized Connection getConnection() throws ConnectionPoolException {
        PooledConnection connection = null;
        while (connection == null) {
            try {
                if (!freeConnections.isEmpty()) {
                    connection = freeConnections.take();
                    if (!connection.isValid(validateConnectionTimeout)) {
                        try {
                            connection.getConnection().close();
                        } catch (SQLException e) {
                        }
                        connection = null;
                    }
                } else if (usedConnections.size() < maxSize) {
                    connection = createConnection();
                } else {
                    throw new ConnectionPoolException(String.format("The database connections number exceed limit %d", maxSize));
                }
            } catch (InterruptedException | SQLException e) {
                throw new ConnectionPoolException("It is impossible to connect to a database", e);
            }
        }
        usedConnections.add(connection);
        System.out.println("Connection was received from pool. " + "It is " + usedConnections.size()
                + " used connection(s) and " + freeConnections.size() + " free connection(s).");
/*        LOGGER.debug("Connection was received from pool. " + "It is " + usedConnections.size()
                + " used connection(s) and " + freeConnections.size() + " free connection(s).");*/
        //ConnectionPool connectionPool = ConnectionPool.getInstance(); // TODO: 06.08.2017 I don't think, that this line is needed
        return connection;
    }

    public synchronized void closeConnection(PooledConnection connection) {
        try {
            if (connection.isValid(validateConnectionTimeout)) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnections.remove(connection);
                freeConnections.put(connection);
                System.out.println("Connection was returned into pool. " + "It is " + usedConnections.size()
                        + " used connection(s) and " + freeConnections.size() + " free connection(s).");
/*                LOGGER.debug("Connection was returned into pool. " + "It is " + usedConnections.size()
                    + " used connection(s) and " + freeConnections.size() + " free connection(s).");*/
            }
        } catch (SQLException | InterruptedException e1) {
            //LOGGER.warn("It is impossible to return connection into pool" + e1);
            try {
                connection.getConnection().close();
            } catch (SQLException e2) {
            }
        }
    }

    private PooledConnection createConnection() throws SQLException {
        return new PooledConnection(DriverManager.getConnection(url, user, password));
    }

    public synchronized void destroy() {
        usedConnections.addAll(freeConnections);
        freeConnections.clear();
        for (PooledConnection connection : usedConnections) {
            try {
                connection.getConnection().close();
            } catch (SQLException e) {
                //LOGGER.error("It's impossible to close connection. " + e);
            }
        }
        usedConnections.clear();
    }
}
