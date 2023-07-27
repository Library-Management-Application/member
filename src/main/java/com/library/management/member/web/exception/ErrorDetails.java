package com.library.management.member.web.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor

public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String cause;

    public ErrorDetails(Date timestamp, String message, String details) {

        this.timestamp = timestamp;
        this.message = message;
        this.cause = details;
    }
}
