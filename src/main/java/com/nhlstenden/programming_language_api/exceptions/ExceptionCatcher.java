package com.nhlstenden.programming_language_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

@RestControllerAdvice
public class ExceptionCatcher {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({SAXException.class, SAXParseException.class})
    public ErrorMessage handleSAXException(SAXException exception) {
        return new ErrorMessage("Validation error: " + exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TransformerErrorException.class)
    public ErrorMessage handleTransformerErrorException(RuntimeException exception) {
        return new ErrorMessage(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidSchemaException.class)
    public ErrorMessage handleInvalidSchemaException(RuntimeException exception) {
        return new ErrorMessage(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ErrorMessage handleObjectNotFoundException(RuntimeException exception) {
        return new ErrorMessage(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidIdException.class)
    public ErrorMessage handleInvalidIdException(RuntimeException exception) {
        return new ErrorMessage(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ErrorMessage handleAnyRuntimeException(RuntimeException exception) {
        return new ErrorMessage("Something unexpected went wrong: " + exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorMessage handleAnyException(Exception exception) {
        return new ErrorMessage("Something unexpected went wrong: " + exception.getMessage());
    }
}