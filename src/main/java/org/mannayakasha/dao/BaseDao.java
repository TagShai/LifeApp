package org.mannayakasha.dao;

import org.mannayakasha.model.Entity;

/**
 * Base class for all dao`s.
 *
 * @author Pavel
 * @version 1.0 28.08.2017.
 */
public abstract class BaseDao<T extends Entity> {
    protected DBConnector connector;

    public DBConnector getConnector() {
        return connector;
    }

    public void setConnector(DBConnector connector) {
        this.connector = connector;
    }

}
