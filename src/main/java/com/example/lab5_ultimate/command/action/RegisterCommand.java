package com.example.lab5_ultimate.command.action;

import com.example.lab5_ultimate.command.CommandExecutionException;
import com.example.lab5_ultimate.command.ServletCommand;
import com.example.lab5_ultimate.command.loader.LoadIndexCommand;
import com.example.lab5_ultimate.db.DaoException;
import com.example.lab5_ultimate.db.UserDao;
import com.example.lab5_ultimate.entity.UserEntity;
import jakarta.persistence.NoResultException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RegisterCommand implements ServletCommand {
    private final UserDao userDao;

    public RegisterCommand(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserEntity user = new UserEntity();
        user.setName(login);
        user.setPassword(password);
        user.setRole("user");
        user.setAccount(100);
        try {
            userDao.create(user);
        } catch (NoResultException e) {
            throw new CommandExecutionException(401,"wrong username or password");
        } catch (DaoException e) {
            throw new CommandExecutionException(400, e.getMessage());
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user.getName());
        session.setAttribute("role", user.getRole());
        new LoadIndexCommand(user.getName(), user.getRole()).execute(request, response);
    }
}
