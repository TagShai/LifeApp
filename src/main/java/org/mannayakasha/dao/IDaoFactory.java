package org.mannayakasha.dao;

import org.mannayakasha.dao.exception.DaoException;

/**
 * Factory for getting a needed type of dao
 *
 * @author Pavel
 * @version  28.08.2017.
 */
public interface IDaoFactory {

    /** Returns distinct DAO implementation for distinct DAO interfaces.
     * @param key - Distinct DAO interfaces.
     * @param <T> - Distinct DAO implementation.
     * @return Distinct DAO implementation.
     * @throws DaoException - if fails. */
    <T extends IDao<?>> T createDao(Class<? extends IDao<?>> key) throws DaoException;

    /** Free system resources. */
    void close();
}
