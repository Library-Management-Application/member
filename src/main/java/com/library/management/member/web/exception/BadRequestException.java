package com.library.management.member.web.exception;


public class BadRequestException extends RuntimeException {
    public BadRequestException(String errorMessage)
    {
        super(errorMessage);
    }
}
