package com.stackroute.movie.exception;

public class MovieAlreadyExistsException extends Exception {
    public MovieAlreadyExistsException(String message) {
        super(message);
    }
}
