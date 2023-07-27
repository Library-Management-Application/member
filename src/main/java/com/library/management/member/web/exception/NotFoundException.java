package com.library.management.member.web.exception;


public class NotFoundException extends RuntimeException {
    public NotFoundException(String errorMessage)
    {
        super(errorMessage);
    }
}
