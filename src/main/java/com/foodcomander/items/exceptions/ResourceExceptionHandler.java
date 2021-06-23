package com.foodcomander.items.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<StandardException> objectNotFound(
      ObjectNotFoundException e, HttpServletRequest request) {
    StandardException error =
        new StandardException(
            HttpStatus.NOT_FOUND.value(),
            "Could not find object",
            e.getMessage(),
            System.currentTimeMillis(),
            request.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }
}
