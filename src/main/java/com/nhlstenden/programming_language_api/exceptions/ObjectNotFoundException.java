package com.nhlstenden.programming_language_api.exceptions;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(long id, String objectName){
        super(String.format("Given id %o does not exist for object %s", id, objectName));
    }


    public ObjectNotFoundException(String languageName){
        super(String.format("Given language %s does not exist in the database", languageName));
    }
}
