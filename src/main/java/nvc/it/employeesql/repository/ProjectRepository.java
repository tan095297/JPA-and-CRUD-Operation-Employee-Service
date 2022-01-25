package nvc.it.employeesql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nvc.it.employeesql.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{
    
}
