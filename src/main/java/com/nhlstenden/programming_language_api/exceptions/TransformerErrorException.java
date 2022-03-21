package com.nhlstenden.programming_language_api.exceptions;

public class TransformerErrorException extends RuntimeException{
    public TransformerErrorException(String objectType){
        super(String.format("Something went wrong transforming the %s", objectType));
    }
}
