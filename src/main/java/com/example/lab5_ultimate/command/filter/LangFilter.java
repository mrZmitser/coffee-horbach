package com.example.lab5_ultimate.command.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Locale;

@WebFilter(filterName = "LanguageFilter", urlPatterns = {"/*"})
public class LangFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getSession().getAttribute("userLocale") == null) {
            req.getSession().setAttribute("userLocale", req.getLocale());
        }
        String lang = request.getParameter("lang");
        if (lang != null) {
            Locale locale = new Locale(lang);
            req.getSession().setAttribute("userLocale", locale);
        }
        chain.doFilter(request, response);
    }
}
