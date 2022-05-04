package com.example.lab5_ultimate.command.loader;

import com.example.lab5_ultimate.command.CommandExecutionException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoadErrorPage {
    public void execute(HttpServletRequest request, HttpServletResponse response, CommandExecutionException e) throws CommandExecutionException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("error.jsp");
        request.setAttribute("code", e.getCode());
        request.setAttribute("message", e.getMessage());
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            throw new CommandExecutionException(500, ex.getMessage());
        }
    }
}
