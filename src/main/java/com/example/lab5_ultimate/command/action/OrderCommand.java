package com.example.lab5_ultimate.command.action;

import com.example.lab5_ultimate.command.CommandExecutionException;
import com.example.lab5_ultimate.command.ServletCommand;
import com.example.lab5_ultimate.db.*;
import com.example.lab5_ultimate.entity.AddIngredientEntity;
import com.example.lab5_ultimate.entity.DrinkEntity;
import com.example.lab5_ultimate.entity.UserEntity;
import jakarta.persistence.NoResultException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.PrintWriter;

@AllArgsConstructor
public class OrderCommand implements ServletCommand {
    UserDao userDao;
    DrinkDao drinkDao;
    IngredientsDao ingredientDao;
    AdditionalIngredientsDao addIngredientsDao;
    String username;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        String drinkID = request.getParameter("drink");
        String[] addIngrIDs = request.getParameterValues("ingr");


        try {
            UserEntity user = userDao.readByName(username);
            DrinkEntity drink = drinkDao.readById(Integer.parseInt(drinkID));
            if (addIngrIDs != null) {
                for (String id : addIngrIDs) {
                    addIngredientsDao.addQuantity(Integer.parseInt(id), -20);
                }
            }
            drinkDao.buyDrink(drink, user, ingredientDao, userDao);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("preparing.jsp");
            requestDispatcher.forward(request, response);
        } catch (DaoException | NoResultException e) {
            throw new CommandExecutionException(404, "not found:" + e.getMessage());
        } catch (ServletException | IOException e) {
            throw new CommandExecutionException(500, e.getMessage());
        }
    }
}
