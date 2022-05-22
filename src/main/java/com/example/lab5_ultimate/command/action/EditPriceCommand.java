package com.example.lab5_ultimate.command.action;

import com.example.lab5_ultimate.command.CommandExecutionException;
import com.example.lab5_ultimate.command.ServletCommand;
import com.example.lab5_ultimate.command.loader.LoadBuyCommand;
import com.example.lab5_ultimate.command.loader.LoadServiceCommand;
import com.example.lab5_ultimate.db.AdditionalIngredientsDao;
import com.example.lab5_ultimate.db.DaoException;
import com.example.lab5_ultimate.db.DrinkDao;
import com.example.lab5_ultimate.db.IngredientsDao;
import com.example.lab5_ultimate.entity.DrinkEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditPriceCommand implements ServletCommand {
    String role;
    DrinkDao drinkDao;
    AdditionalIngredientsDao addIngredientsDao;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        if (!role.equals("admin")) {
            throw new CommandExecutionException(403, "Not enough rights");
        }
        String id = request.getParameter("id");
        String newPrice = request.getParameter("new_price");
        try {
            DrinkEntity drink = drinkDao.readById(Integer.parseInt(id));
            drink.setCost(Double.parseDouble(newPrice));
            drinkDao.update(drink);
        } catch (DaoException e){
            throw new CommandExecutionException(400, e.getMessage());
        }
        new LoadBuyCommand(role, drinkDao, addIngredientsDao).execute(request, response);
    }
}
