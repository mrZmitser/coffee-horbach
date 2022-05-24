package com.example.lab5_ultimate.command.loader;

import com.example.lab5_ultimate.command.CommandExecutionException;
import com.example.lab5_ultimate.command.ServletCommand;
import com.example.lab5_ultimate.db.AdditionalIngredientsDao;
import com.example.lab5_ultimate.db.DaoException;
import com.example.lab5_ultimate.db.DrinkDao;
import com.example.lab5_ultimate.db.IngredientsDao;
import com.example.lab5_ultimate.entity.AddIngredientEntity;
import com.example.lab5_ultimate.entity.IngredientEntity;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
public class LoadServiceCommand implements ServletCommand {
    String role;
    IngredientsDao ingredientDao;
    AdditionalIngredientsDao addIngredientsDao;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        if (!role.equals("admin")) {
            throw new CommandExecutionException(403, "Not enough rights");
        }
        try {
            request.setAttribute("role", role);
            request.setAttribute("ingrs", ingredientDao.read().stream().sorted(Comparator.comparingInt(IngredientEntity::getId)).collect(Collectors.toList()));
            request.setAttribute("addIngrs", addIngredientsDao.read().stream().sorted(Comparator.comparingInt(AddIngredientEntity::getId)).collect(Collectors.toList()));
        } catch (DaoException e) {
            throw new CommandExecutionException(500, e.getMessage());
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("service.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new CommandExecutionException(500, e.getMessage());
        }
    }
}
