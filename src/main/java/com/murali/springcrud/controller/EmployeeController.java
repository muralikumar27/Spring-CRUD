package com.murali.springcrud.controller;

import com.murali.springcrud.entity.Employee;
import com.murali.springcrud.exception.EmployeeAlreadyExistException;
import com.murali.springcrud.exception.EmployeeNotFoundException;
import com.murali.springcrud.model.EmployeeModel;
import com.murali.springcrud.model.UpdateModel;
import com.murali.springcrud.response.Response;
import com.murali.springcrud.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @PostMapping("/add-employee")
    public ResponseEntity<Response> addEmployee(@RequestBody EmployeeModel employeeModel) throws EmployeeAlreadyExistException {
        Response response = new Response("Employee added successfully...",HttpStatus.OK);
        employeeService.saveEmployee(employeeModel);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public Employee getEmployeeById(@PathVariable("id") long id) throws EmployeeNotFoundException {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("get-all")
    public List<Employee>employees(){
        return employeeService.getAllEmployees();
    }

    @PutMapping("update-employee/{id}")
    public Employee updateEmployee(@PathVariable("id") long id, @RequestBody UpdateModel updateModel) throws EmployeeNotFoundException {
        return employeeService.updateEmployee(id,updateModel);
    }

    @DeleteMapping("delete-employee/{id}")
    public ResponseEntity<Response>deleteEmployee(@PathVariable("id") long id) throws EmployeeNotFoundException {
        employeeService.deleteEmployee(id);
        Response response = new Response("Deleted successfully",HttpStatus.OK);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
