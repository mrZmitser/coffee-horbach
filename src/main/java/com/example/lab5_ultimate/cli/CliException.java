package com.example.lab5_ultimate.cli;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter @AllArgsConstructor @ToString
public class CliException extends Exception {
    private String message;
}
