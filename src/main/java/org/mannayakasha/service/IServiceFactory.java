package org.mannayakasha.service;

import org.mannayakasha.service.exception.ServiceException;

/**
 * Provides access to service-classes for interact dao-classes.
 *
 * @author Pavel
 * @version 1.0 28.08.2017.
 */
public interface IServiceFactory {
    <Type extends IService> Type getService(Class<? extends IService> key) throws ServiceException;

    void close();
}
