package com.example.lab5_ultimate.command;

import lombok.Getter;

public class CommandExecutionException extends Exception {
    @Getter
    private final int code;
    @Getter
    private final String message;

    public CommandExecutionException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
