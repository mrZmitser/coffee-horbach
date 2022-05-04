package com.example.lab5_ultimate.command.loader;

import com.example.lab5_ultimate.command.CommandExecutionException;
import com.example.lab5_ultimate.command.ServletCommand;
import com.example.lab5_ultimate.db.AdditionalIngredientsDao;
import com.example.lab5_ultimate.db.DaoException;
import com.example.lab5_ultimate.db.DrinkDao;
import com.example.lab5_ultimate.entity.AddIngredientEntity;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class LoadBuyCommand implements ServletCommand {
    String role;
    DrinkDao drinkDao;
    AdditionalIngredientsDao addIngredientsDao;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        try {
            request.setAttribute("role", role);
            request.setAttribute("drinks", drinkDao.read());
            request.setAttribute("ingrs", addIngredientsDao.read());
        } catch (DaoException e) {
            throw new CommandExecutionException(500, e.getMessage());
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("buy.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw  new CommandExecutionException(500, e.getMessage());
        }
    }
}
