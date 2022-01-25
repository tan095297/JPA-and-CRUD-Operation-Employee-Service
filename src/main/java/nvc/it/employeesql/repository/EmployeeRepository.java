package nvc.it.employeesql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nvc.it.employeesql.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee , Integer> {
    public List<Employee> findByNameContaining(String name) ;
    public List<Employee> findBySalaryGreaterThanEqual(double salary) ;
}
