package com.example.lab5_ultimate.command.action;

import com.example.lab5_ultimate.command.CommandExecutionException;
import com.example.lab5_ultimate.command.ServletCommand;
import com.example.lab5_ultimate.command.loader.LoadServiceCommand;
import com.example.lab5_ultimate.db.AdditionalIngredientsDao;
import com.example.lab5_ultimate.db.DaoException;
import com.example.lab5_ultimate.db.DrinkDao;
import com.example.lab5_ultimate.db.IngredientsDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ServiceCommand implements ServletCommand {
    String role;
    boolean additional;
    DrinkDao drinkDao;
    IngredientsDao ingredientDao;
    AdditionalIngredientsDao addIngredientsDao;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        if (!role.equals("admin")) {
            throw new CommandExecutionException(403, "Not enough rights");
        }
        String ingrID = request.getParameter("id");
        String quantity = request.getParameter("quantity");
        try {
            if (additional) {
                addIngredientsDao.addQuantity(Integer.parseInt(ingrID), Double.parseDouble(quantity));
            } else {
                ingredientDao.addQuantity(Integer.parseInt(ingrID), Double.parseDouble(quantity));
            }
        } catch (DaoException e) {
           throw new CommandExecutionException(400, e.getMessage());
        }
        new LoadServiceCommand(role, ingredientDao, addIngredientsDao).execute(request, response);
    }
}
