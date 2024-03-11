package com.murali.springcrud.exception;

public class EmployeeNotFoundException extends Exception{
    public EmployeeNotFoundException(String msg){
        super(msg);
    }
}
