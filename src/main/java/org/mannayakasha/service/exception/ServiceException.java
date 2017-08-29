package org.mannayakasha.service.exception;

/**
 * Exceptions, that can occur in services.
 *
 * @author Pavel
 * @version 1.0 28.08.2017.
 */
public class ServiceException extends Exception {
    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
