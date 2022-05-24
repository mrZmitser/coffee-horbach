package com.example.lab5_ultimate.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ServletCommand {
    void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException;
}
