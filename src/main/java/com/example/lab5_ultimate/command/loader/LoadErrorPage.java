package com.example.lab5_ultimate.command.loader;

import com.example.lab5_ultimate.command.CommandExecutionException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
