package com.example.lab5_ultimate.command.action;

import com.example.lab5_ultimate.command.CommandExecutionException;
import com.example.lab5_ultimate.command.ServletCommand;
import com.example.lab5_ultimate.command.loader.LoadLoginCommand;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutCommand implements ServletCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        session.setAttribute("role", null);
        new LoadLoginCommand().execute(request, response);
    }
}
