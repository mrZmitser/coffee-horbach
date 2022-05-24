package com.example.lab5_ultimate.db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * The general DAO that connects to the database
 */
public abstract class Dao {
    protected EntityManagerFactory entityManagerFactory;

    public Dao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
    }
}
