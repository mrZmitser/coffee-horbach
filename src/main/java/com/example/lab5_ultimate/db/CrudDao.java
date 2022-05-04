package com.example.lab5_ultimate.db;

import java.util.List;

/**
 * CrudDao is a simple abstract DAO for general CRUD operation
 * @param <T> a class to perform CRUD operations with
 */
public abstract class CrudDao<T> extends Dao {
    public CrudDao() {
        super();
    }

    /**
     * Adds an object to the database
     * @param t an object to write
     * @throws DaoException thrown if there's database error (SQL error or
     * constraints violation)
     */
    public abstract void create(T t) throws DaoException;

    /**
     * Reads all the objects of the type to the database
     * @return the array of the objects
     * @throws DaoException thrown if there's database error (SQL error or
     * constraints violation)
     */
    public abstract List<T> read() throws DaoException;

    /**
     * Selects the object with the given ID
     * @param id ID of the object
     * @return the object with the given ID
     * @throws DaoException thrown if there's database error (SQL error or
     * constraints violation)
     */
    public abstract T readById(int id) throws DaoException;

    /**
     * Updates the object to the new one
     * @param t a new object
     * @throws DaoException thrown if there's database error (SQL error or
     * constraints violation)
     */
    public abstract void update(T t) throws DaoException;

    /**
     * Deletes the object with the given ID
     * @param t object to delete
     * @throws DaoException thrown if there's database error (SQL error or
     * constraints violation)
     */
    public abstract void delete(T t) throws DaoException;
}
