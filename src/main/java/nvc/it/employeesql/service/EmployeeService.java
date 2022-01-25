package nvc.it.employeesql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nvc.it.employeesql.model.Employee;
import nvc.it.employeesql.repository.EmployeeRepository;


@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository ;

    //Get all emp
    public List<Employee>findAll(){
        return employeeRepository.findAll();
    }

    //Get emp by Name
    public List<Employee>findByName(String name){
        return employeeRepository.findByNameContaining(name);
    }

    //Get emp by salary
    public List<Employee>findBySalaryGreaterThanEqual(Double salary){
        return employeeRepository.findBySalaryGreaterThanEqual(salary);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    public Employee getById(int id) {
        return employeeRepository.getById(id);
    }

   
    
}
