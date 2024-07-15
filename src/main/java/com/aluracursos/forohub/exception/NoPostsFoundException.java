package com.aluracursos.forohub.exception;

public class NoPostsFoundException extends RuntimeException{
    public NoPostsFoundException(String message) {
        super(message);
    }
}
