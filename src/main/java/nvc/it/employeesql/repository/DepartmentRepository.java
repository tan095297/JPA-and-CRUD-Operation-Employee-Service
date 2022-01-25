package nvc.it.employeesql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nvc.it.employeesql.model.Department;

public interface DepartmentRepository extends JpaRepository<Department , Integer>{
    
}
