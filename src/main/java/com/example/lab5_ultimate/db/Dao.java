package com.example.lab5_ultimate.db;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * The general DAO that connects to the database
 */
public abstract class Dao {
    protected EntityManagerFactory entityManagerFactory;

    public Dao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
    }
}
