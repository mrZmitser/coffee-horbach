package com.example.lab5_ultimate.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ServletCommand {
    void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException;
}
