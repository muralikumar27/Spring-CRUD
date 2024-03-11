package com.murali.springcrud.exception;

import com.murali.springcrud.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseStatus
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(EmployeeAlreadyExistException.class)
    public ResponseEntity<Response> EmployeeExistsException(EmployeeAlreadyExistException eae){
        Response response = new Response(eae.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Response> EmployeeNotFoundException(EmployeeNotFoundException enf){
        Response response = new Response(enf.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<Response> GeneralException(Exception e){
        Response response = new Response("Error try again", HttpStatus.INTERNAL_SERVER_ERROR);
        System.out.println(e.getMessage());
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
