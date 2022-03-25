package com.nhlstenden.programming_language_api.exceptions;

public class InvalidIdException extends RuntimeException {
    public InvalidIdException() {
        super("Error: supplied ID is not valid");
    }
}
