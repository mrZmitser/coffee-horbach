package com.example.lab5_ultimate.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseCreator {
    public static void createAll(Connection connection) throws SQLException {
        createUsers(connection);
        createIngredients(connection);
        createDrinks(connection);
        createAdditionalIngredients(connection);
        createReceipts(connection);
    }

    public static void createUsers(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS users(" +
                        "id SERIAL PRIMARY KEY," +
                        "name VARCHAR UNIQUE," +
                        "account NUMERIC)");
        statement.execute();
        statement.close();
    }

    public static void createIngredients(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS ingredients(" +
                        "id SERIAL PRIMARY KEY," +
                        "name VARCHAR UNIQUE," +
                        "quantity NUMERIC)");
        statement.execute();
        statement.close();
    }

    public static void createAdditionalIngredients(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS add_ingredients(" +
                        "id SERIAL PRIMARY KEY," +
                        "name VARCHAR UNIQUE," +
                        "quantity NUMERIC)");
        statement.execute();
        statement.close();
    }

    public static void createDrinks(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS drinks(" +
                        "id SERIAL PRIMARY KEY," +
                        "name VARCHAR UNIQUE," +
                        "cost NUMERIC)");
        statement.execute();
        statement.close();
    }

    public static void createReceipts(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS receipts(" +
                        "id SERIAL PRIMARY KEY," +
                        "drink_id INT REFERENCES drinks(id)," +
                        "ingredient_id INT REFERENCES ingredients(id)," +
                        "quantity NUMERIC)");
        statement.execute();
        statement.close();
    }
}