package com.multitel.mql.mqlservice.domain;

public class InvalidQueryException extends RuntimeException {
    public InvalidQueryException(String message) {
        super(message);
    }
}
