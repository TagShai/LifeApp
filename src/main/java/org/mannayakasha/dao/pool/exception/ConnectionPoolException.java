package org.mannayakasha.dao.pool.exception;

/**
 * Exceptions, that can occur in connection pool.
 *
 * @author Pavel
 * @version 1.0 04.08.2017.
 */
public class ConnectionPoolException extends Exception {
    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
}
