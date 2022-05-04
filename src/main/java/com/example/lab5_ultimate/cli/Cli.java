package com.example.lab5_ultimate.cli;

import com.example.lab5_ultimate.db.*;
import com.example.lab5_ultimate.entity.AddIngredientEntity;
import com.example.lab5_ultimate.entity.DrinkEntity;
import com.example.lab5_ultimate.entity.IngredientEntity;
import com.example.lab5_ultimate.entity.UserEntity;

import java.util.List;
import java.util.Scanner;

public class Cli {
    private final UserDao userDao;
    private final DrinkDao drinkDao;
    private final AdditionalIngredientsDao addIngredientsDao;
    private final IngredientsDao ingredientDao;

    public Cli(UserDao userDao, DrinkDao drinkDao,
               AdditionalIngredientsDao addIngredientsDao, IngredientsDao ingredientDao) {
        this.userDao = userDao;
        this.drinkDao = drinkDao;
        this.addIngredientsDao = addIngredientsDao;
        this.ingredientDao = ingredientDao;
    }

    public void start() {
        String cmd = "";
        Scanner scanner = new Scanner(System.in);
        while (!cmd.equals("exit")) {
            System.out.print("> ");
            cmd = scanner.nextLine();
            String[] args = cmd.split(" ");
            try {
                switch (args[0]) {
                    case "drinks":
                        drinkDao.read().forEach(System.out::println);
                        break;
                    case "ingr":
                        ingredientDao.read().forEach(System.out::println);
                        break;
                    case "account":
                        accountCmd(args, userDao);
                        break;
                    case "full":
                        fullCmd(ingredientDao, addIngredientsDao);
                        break;
                    case "buy":
                        buyCmd(args, userDao, ingredientDao);
                        break;
                }
            } catch (CliException e) {
                System.out.println(e.getMessage());
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
    }

    private void buyCmd(String[] args, UserDao userDao, IngredientsDao ingredientsDao) throws CliException, DaoException {
        if (args.length < 3) {
            throw new CliException("Wrong args number. Usage: buy <USER> <DRINK_NO> (<ADD_INGR_NO>)");
        }
        int drinkId;
        try {
            drinkId = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            throw new CliException("Cannot parse int");
        }
        UserEntity user = userDao.readByName(args[1]);
        if (user == null) {
            throw new CliException("user not found");
        }
        DrinkEntity drink = drinkDao.readById(drinkId);
        drinkDao.buyDrink(drink, user, ingredientsDao, userDao);
    }

    private void fullCmd(IngredientsDao ingredientsDao, AdditionalIngredientsDao additionalIngredientsDao)
            throws CliException, DaoException {
        System.out.println("Do you want to full the main (1) or additional (2) ingredients?");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.nextLine();
        switch (cmd) {
            case "1":
                doFullCmd(ingredientsDao, IngredientEntity.class);
                break;
            case "2":
                doFullCmd(additionalIngredientsDao, AddIngredientEntity.class);
                break;
            default:
                System.out.println("Unrecognized action");
        }
    }


    private <T> void doFullCmd(AbstractIngredientDao<T> ingredientsDao, Class<T> type) throws CliException, DaoException {
        if (type != AddIngredientEntity.class && type != IngredientEntity.class) {
            throw new CliException("Wrong class: not ingredient");
        }
        System.out.println("What ingredient do you want to fill and how much? For example: 1 1000");
        List<T> ingrs = ingredientsDao.read();
        for (int i = 0; i < ingrs.size(); i++) {
            System.out.println(i + "\t" + ingrs.get(i));
        }

        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.nextLine();
        String[] args = cmd.split(" ");
        if (args.length < 2) {
            throw new CliException("Wrong args number. USAGE: <NO_IN_LIST> <QUANTITY>");
        }
        int ingrId;
        double quantity;
        try {
            ingrId = Integer.parseInt(args[0]);
            quantity = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            throw new CliException("Cannot parse int");
        }

        ingredientsDao.addQuantity(ingrId, quantity);
    }

    private void accountCmd(String[] args, UserDao userDao) throws CliException, DaoException {
        if (args.length < 2) {
            throw new CliException("Wrong args len. USAGE: account <username>");
        }
        UserEntity user = userDao.readByName(args[1]);
        if (user == null) {
            throw new CliException("Not found");
        }
        System.out.println(user.getAccount());
    }
}
