package com.nhlstenden.programming_language_api.exceptions;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(long id, String objectName){
        super(String.format("Given id {0} does not exist for object {1}", id, objectName));
    }
}
