package com.murali.springcrud.service;

import com.murali.springcrud.entity.Employee;
import com.murali.springcrud.exception.EmployeeAlreadyExistException;
import com.murali.springcrud.exception.EmployeeNotFoundException;
import com.murali.springcrud.model.EmployeeModel;
import com.murali.springcrud.model.UpdateModel;

import java.util.List;

public interface EmployeeService {
    void saveEmployee(EmployeeModel employeeModel) throws EmployeeAlreadyExistException;

    Employee getEmployeeById(long id) throws EmployeeNotFoundException;

    List<Employee> getAllEmployees();

    Employee updateEmployee(long id,UpdateModel updateModel) throws EmployeeNotFoundException;

    void deleteEmployee(long id) throws EmployeeNotFoundException;
}
