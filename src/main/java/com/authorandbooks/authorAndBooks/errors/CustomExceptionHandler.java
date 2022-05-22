package com.authorandbooks.authorAndBooks.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LibraryException.class)
    public ErrorMessageInfo LibraryException(LibraryException e) {
        return new ErrorMessageInfo(e.getMessage());
    }
}
