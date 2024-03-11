package com.murali.springcrud.exception;

public class EmployeeAlreadyExistException extends Exception{
    public EmployeeAlreadyExistException(String meg){
        super(meg);
    }
}
