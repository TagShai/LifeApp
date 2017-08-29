package org.mannayakasha.dao;

import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.model.Entity;

/**
 * Base dao interfaces, that includes CRUD operations.
 *
 * @author Pavel
 * @version 1.0 28.08.2017.
 */
public interface IDao<T extends Entity> {

    /**
     * Create new instance of some entity
     * @param entity Entity to create
     * @return new entity
     * @throws DaoException if fails
     */
    Integer create(T entity) throws DaoException;

    /*
     * Delete instance of some entity
     * @param entity to delete
     * @throws DaoException if fails
     */
    //void delete (T entity) throws DaoException;

    /**
     * Delete instance of some entity
     * @param id id of entity, that need to delete
     * @throws DaoException if fails
     */
    void delete(int id) throws DaoException;

    /**
     * Update instance of some entity
     * @param entity update
     * @throws DaoException if fails
     */
    void update(T entity) throws DaoException;

    /**
     * Find some entity by id
     * @param id id of the entity
     * @return found entity
     * @throws DaoException if fails
     */
    T findById(int id) throws DaoException;
}