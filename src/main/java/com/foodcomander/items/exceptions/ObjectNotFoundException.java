package com.foodcomander.items.exceptions;

public class ObjectNotFoundException extends RuntimeException {

  private static final String NOTFOUND = "Object not found";

  public ObjectNotFoundException() {
    super(NOTFOUND);
  }
}
