package com.example.lab5_ultimate.command.loader;

import com.example.lab5_ultimate.command.CommandExecutionException;
import com.example.lab5_ultimate.command.ServletCommand;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class LoadChatCommand implements ServletCommand {
    String username;
    String role;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("chat.jsp");
        request.setAttribute("role", role);
        request.setAttribute("sender", username);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new CommandExecutionException(500, e.getMessage());
        }
    }
}
