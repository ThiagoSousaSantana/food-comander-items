package com.foodcomander.items.exceptions;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException() {
        super("Object not found");
    }
}
