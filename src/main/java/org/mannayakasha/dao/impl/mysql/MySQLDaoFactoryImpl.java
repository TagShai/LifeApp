package org.mannayakasha.dao.impl.mysql;

import org.mannayakasha.dao.BaseDao;
import org.mannayakasha.dao.DBConnector;
import org.mannayakasha.dao.IDao;
import org.mannayakasha.dao.IDaoFactory;
import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.interfaces.IAccountDao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Factory for getting a needed type of mysql dao.
 *
 * @author Pavel
 * @version 1.0 28.08.2017.
 */
public class MySQLDaoFactoryImpl implements IDaoFactory {
    //private static final Logger LOGGER = Logger.getLogger(MySQLDaoFactoryImpl.class);

    private static Map<Class<? extends IDao<?>>, Class<? extends BaseDao>> DAO_MAP = new ConcurrentHashMap<>();

    private DBConnector connector;

    static {
        DAO_MAP.put(IAccountDao.class, AccountDaoImpl.class);
    }

    public MySQLDaoFactoryImpl() throws DaoException {
        connector = new DBConnector();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends IDao<?>> T createDao(Class<? extends IDao<?>> key) throws DaoException {
        Class<? extends BaseDao> value = DAO_MAP.get(key);
        if(value != null) {
            try {
                BaseDao dao = value.newInstance();
                dao.setConnector(connector);
                connector.getConnection();
                return (T)dao;
            } catch(InstantiationException | IllegalAccessException e) {
                //LOGGER.error("It is impossible to create DAO", e);
                throw new DaoException(e.getMessage(), e.getCause());
            }
        }
        return null; // TODO: 28.08.2017 Maybe better throw some exception
    }

    @Override
    public void close() { // close connection and return it into connection pool
        if (connector != null) { // TODO: 28.08.2017 Close not only connection, but also close transaction. Need to think about it
            connector.closeConnection();
        }
    }
}
