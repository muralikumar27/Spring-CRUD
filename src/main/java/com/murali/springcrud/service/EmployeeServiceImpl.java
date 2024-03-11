package com.murali.springcrud.service;

import com.murali.springcrud.entity.Employee;
import com.murali.springcrud.exception.EmployeeAlreadyExistException;
import com.murali.springcrud.exception.EmployeeNotFoundException;
import com.murali.springcrud.model.EmployeeModel;
import com.murali.springcrud.model.UpdateModel;
import com.murali.springcrud.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    @Override
    public void saveEmployee(EmployeeModel employeeModel) throws EmployeeAlreadyExistException {
        Employee employee = new Employee();
        employee.setId(employeeModel.getId());
        employee.setName(employeeModel.getName());
        employee.setRole(employeeModel.getRole());
        employee.setSalary(employeeModel.getSalary());
        if(employeeRepository.existsById(employee.getId())){
            throw new EmployeeAlreadyExistException("Employee exists with this ID");
        }
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(long id) throws EmployeeNotFoundException {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isEmpty()){
            throw new EmployeeNotFoundException("No Employee exists for this ID");
        }
        return optionalEmployee.get();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    @Override
    public Employee updateEmployee(long id, UpdateModel updateModel) throws EmployeeNotFoundException {
        Optional<Employee>optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()){
            throw new EmployeeNotFoundException("Update failed,No employee found");
        }
        Employee employee = optionalEmployee.get();
        if(Objects.nonNull(updateModel.getName()) && !updateModel.getName().isEmpty()){
            employee.setName(updateModel.getName());
        }
        if(updateModel.getRole() != null && !updateModel.getRole().isEmpty()){
            employee.setRole(updateModel.getRole());
        }
        if(updateModel.getSalary() > 0){
            employee.setSalary(updateModel.getSalary());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(long id) throws EmployeeNotFoundException {
        if(!employeeRepository.existsById(id)){
            throw new EmployeeNotFoundException("Deletion failed no employee found");
        }
        employeeRepository.deleteById(id);
    }
}
