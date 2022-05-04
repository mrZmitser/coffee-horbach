package com.example.lab5_ultimate.command.filter;

import com.example.lab5_ultimate.command.CommandExecutionException;
import com.example.lab5_ultimate.command.action.LoginCommand;
import com.example.lab5_ultimate.command.action.RegisterCommand;
import com.example.lab5_ultimate.command.loader.LoadIndexCommand;
import com.example.lab5_ultimate.command.loader.LoadLoginCommand;
import com.example.lab5_ultimate.db.UserDao;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter implements Filter {
    UserDao userDao;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        userDao = new UserDao();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        HttpServletResponse response = ((HttpServletResponse) servletResponse);
        String path = request.getServletPath();
        if (path.equals("/register.jsp")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        String role = (String) session.getAttribute("role");
        if (user == null || role == null) {
            try {
                String action = request.getParameter("action");
                String method = request.getMethod();
                if (action == null) {
                    new LoadIndexCommand("", "").execute(request, response);
                } else if (method.equals("POST") && action.equals("login") ) {
                    new LoginCommand(userDao).execute(request, response);
                } else if (method.equals("GET") &&  action.equals("login")) {
                    new LoadLoginCommand().execute(request, response);
                } else if (method.equals("POST") && action.equals("register")) {
                    new RegisterCommand(userDao).execute(request, response);
                } else {
                    new LoadLoginCommand().execute(request, response);
                }
                return;
            } catch (CommandExecutionException e) {
                e.printStackTrace();
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
