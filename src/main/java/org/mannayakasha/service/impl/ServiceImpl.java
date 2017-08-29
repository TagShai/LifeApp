package org.mannayakasha.service.impl;

import org.mannayakasha.dao.IDaoFactory;
import org.mannayakasha.service.IService;

/**
 * Implementation of {@link org.mannayakasha.service.IService} interface.
 *
 * @author Pavel
 * @version 1.0 28.08.2017.
 */
public class ServiceImpl implements IService {
    protected IDaoFactory factory = null;

    public void setDaoFactory(IDaoFactory factory) {
        this.factory = factory;
    }
}
