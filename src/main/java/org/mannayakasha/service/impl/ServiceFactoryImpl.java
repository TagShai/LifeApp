package org.mannayakasha.service.impl;

import org.mannayakasha.dao.IDaoFactory;
import org.mannayakasha.service.IService;
import org.mannayakasha.service.IServiceFactory;
import org.mannayakasha.service.exception.ServiceException;
import org.mannayakasha.service.interfaces.IAccountService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of {@link org.mannayakasha.service.IServiceFactory} interface.
 *
 * @author Pavel
 * @version 1.0 29.08.2017.
 */
public class ServiceFactoryImpl implements IServiceFactory {
    //private static final Logger LOGGER = Logger.getLogger(ServiceFactoryImpl.class);

    private static final Map<Class<? extends IService>, Class<? extends ServiceImpl>> SERVICES = new ConcurrentHashMap<>(); // TODO: 04.08.2017 ConcurrentHashMap

    static {
        SERVICES.put(IAccountService.class, AccountServiceImpl.class);
    }

    private IDaoFactory factory;

    public ServiceFactoryImpl(final IDaoFactory factory) throws ServiceException {
        this.factory = factory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <Type extends IService> Type getService(Class<? extends IService> key) throws ServiceException {
        Class<? extends ServiceImpl> value = SERVICES.get(key);
        if(value != null) {
            try {
                ServiceImpl service = value.newInstance();
                service.setDaoFactory(factory);
                return (Type)service;
            } catch(InstantiationException | IllegalAccessException e) {
                //LOGGER.error("It is impossible to instance service class", e);
                throw new ServiceException(e.getMessage(), e.getCause());
            }
        }
        return null; // TODO: 06.08.2017 return null is not good
    }

    @Override
    public void close() {
        factory.close();
    }
}
