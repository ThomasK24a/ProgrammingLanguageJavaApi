package com.nhlstenden.programming_language_api.exceptions;

public class InvalidSchemaException extends RuntimeException{
    public InvalidSchemaException(String schemaObjectName){
        super(String.format("Schema for {0} does not exist or is not valid", schemaObjectName));
    }
}
