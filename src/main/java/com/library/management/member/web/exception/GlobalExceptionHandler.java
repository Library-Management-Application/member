package com.library.management.member.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> constraintViolationExceptionHandler(ConstraintViolationException ex){
        List<String> errorsList = new ArrayList<>(ex.getConstraintViolations().size());

        ex.getConstraintViolations().forEach(error -> errorsList.add(error.toString()));

        return new ResponseEntity<>(errorsList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> notFoundExceptionHandler(NotFoundException ex){

        ErrorDetails errorDetail = new ErrorDetails(new Date(), "Not Found", ex.getMessage());

        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetails> badRequestExceptionHandler(BadRequestException ex){

        ErrorDetails errorDetail = new ErrorDetails(new Date(), "Bad Request", ex.getMessage());

        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

}
