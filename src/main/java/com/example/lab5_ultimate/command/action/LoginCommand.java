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

public class LoginCommand implements ServletCommand {
    private final UserDao userDao;

    public LoginCommand(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserEntity user;
        try {
            user = userDao.readByName(login);
        } catch (NoResultException e) {
            throw new CommandExecutionException(401,"wrong username or password");
        } catch (DaoException e) {
            throw new CommandExecutionException(400, e.getMessage());
        }
        if (!password.equals(user.getPassword())) {
            throw new CommandExecutionException(401, "wrong username or password");
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user.getName());
        session.setAttribute("role", user.getRole());
//        response.addCookie(new Cookie("user", user.getName()));
//        response.addCookie(new Cookie("role", user.getRole()));
        new LoadIndexCommand(user.getName(), user.getRole()).execute(request, response);
    }
}
