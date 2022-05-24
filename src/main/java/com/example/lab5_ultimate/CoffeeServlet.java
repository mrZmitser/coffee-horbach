package com.example.lab5_ultimate;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.example.lab5_ultimate.command.*;
import com.example.lab5_ultimate.command.action.*;
import com.example.lab5_ultimate.command.loader.*;
import com.example.lab5_ultimate.db.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "coffeeServlet", urlPatterns = "/coffee-servlet")
public class CoffeeServlet extends HttpServlet {
    AdditionalIngredientsDao addIngredientsDao;
    DrinkDao drinkDao;
    IngredientsDao ingredientDao;
    UserDao userDao;

    public void init() {
        addIngredientsDao = new AdditionalIngredientsDao();
        drinkDao = new DrinkDao();
        ingredientDao = new IngredientsDao();
        userDao = new UserDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String action = request.getParameter("action");
        ServletCommand cmd = null;
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        String role = (String) session.getAttribute("role");

        if (action == null) {
            startNewSessionAndSaveCookies(request, response);
            cmd = new LoadIndexCommand(user, role);
        } else {
            switch (action) {
                case "login":
                    cmd = new LoadLoginCommand();
                    break;
                case "log-out":
                    cmd = new LogoutCommand();
                    break;
                case "buy":
                    cmd = new LoadBuyCommand(role, drinkDao, addIngredientsDao);
                    break;
                case "service":
                    cmd = new LoadServiceCommand(role, ingredientDao, addIngredientsDao);
                    break;
                case "chat":
                    cmd = new LoadChatCommand(user, role);
                    break;
            }
        }
        if (cmd == null) {
            return;
        }
        try {
            cmd.execute(request, response);
        } catch (CommandExecutionException e) {
            try {
                new LoadErrorPage().execute(request, response, e);
            } catch (CommandExecutionException ex) {
                log("cannot write error", ex);
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        ServletCommand cmd = null;
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        String role = (String) session.getAttribute("role");
        switch (action) {
            case "login":
                cmd = new LoginCommand(userDao);
                break;
            case "order":
                cmd = new OrderCommand(userDao, drinkDao, ingredientDao, addIngredientsDao, user);
                break;
            case "serviceIngr":
                cmd = new ServiceCommand(role, false, drinkDao, ingredientDao, addIngredientsDao);
                break;
            case "serviceAddIngr":
                cmd = new ServiceCommand(role, true, drinkDao, ingredientDao, addIngredientsDao);
                break;
            case "editPrice":
                cmd = new EditPriceCommand(role, drinkDao, addIngredientsDao);
                break;
        }


        try {
            cmd.execute(request, response);
        } catch (CommandExecutionException e) {
            try {
                new LoadErrorPage().execute(request, response, e);
            } catch (CommandExecutionException ex) {
                log("cannot write error", ex);
            }
        }
    }

    private void startNewSessionAndSaveCookies(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        Cookie lastEnterTime = new Cookie("lastEnterTime", new SimpleDateFormat("yyyy-MM-dd-hh:mm").format(new Date()));
        lastEnterTime.setComment("Last session data");
        Cookie usageCount = new Cookie("usageCount", "1");
        usageCount.setComment("Information about visit counts.");

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("usageCount")) {
                    int lastUsageCount = Integer.parseInt(cookie.getValue());
                    lastUsageCount += 1;
                    usageCount.setValue(Integer.toString(lastUsageCount));
                }
            }
        }
        response.addCookie(lastEnterTime);
        response.addCookie(usageCount);
    }

    public void destroy() {
    }
}