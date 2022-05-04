package com.example.lab5_ultimate.command.filter;

import com.example.lab5_ultimate.command.CommandExecutionException;
import com.example.lab5_ultimate.command.loader.LoadRegisterCommand;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RegisterFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String action = request.getParameter("action");
        if (action == null || !action.equals("register")) {
            chain.doFilter(servletRequest, response);
            return;
        }
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (!login.matches("[A-Za-z0-9]{3,}")) {
            try {
                new LoadRegisterCommand("Login must be at least 3 symbols and may only contain latin letters and number")
                        .execute(request, (HttpServletResponse) response);
            } catch (CommandExecutionException e) {
                return;
            }
            return;
        }
        if (!password.matches("[A-Za-z0-9]{8,}")) {
            try {
                new LoadRegisterCommand("Password must be at least 8 symbols and may only contain latin letters and number")
                        .execute(request, (HttpServletResponse) response);
            } catch (CommandExecutionException e) {
                return;
            }
            return;
        }
        chain.doFilter(request, response);
    }
}
