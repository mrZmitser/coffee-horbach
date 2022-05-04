package com.example.lab5_ultimate.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class DaoException extends Exception {
    private String message;
}
