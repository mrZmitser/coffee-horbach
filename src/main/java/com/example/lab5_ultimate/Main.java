package com.example.lab5_ultimate;

import com.example.lab5_ultimate.cli.Cli;
import com.example.lab5_ultimate.db.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        AdditionalIngredientsDao addIngredientsDao = new AdditionalIngredientsDao();
        DrinkDao drinkDao = new DrinkDao();
        IngredientsDao ingredientDao = new IngredientsDao();
        UserDao userDao = new UserDao();
        new Cli(userDao, drinkDao, addIngredientsDao, ingredientDao).start();
    }
}
