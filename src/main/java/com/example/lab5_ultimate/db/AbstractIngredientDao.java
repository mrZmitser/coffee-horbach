package com.example.lab5_ultimate.db;

public abstract class AbstractIngredientDao<T> extends CrudDao<T>  {
    public abstract void addQuantity(int ingrId, double quantity) throws DaoException;
    public abstract void buy(int ingrId, double quantity) throws DaoException;
}
