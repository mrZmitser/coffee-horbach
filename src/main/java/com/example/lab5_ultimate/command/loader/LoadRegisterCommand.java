package com.example.lab5_ultimate.command.loader;

import com.example.lab5_ultimate.command.CommandExecutionException;
import com.example.lab5_ultimate.command.ServletCommand;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class LoadRegisterCommand implements ServletCommand {
    String message;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
         request.setAttribute("errMsg", message);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            throw new CommandExecutionException(500, ex.getMessage());
        }
    }
}
