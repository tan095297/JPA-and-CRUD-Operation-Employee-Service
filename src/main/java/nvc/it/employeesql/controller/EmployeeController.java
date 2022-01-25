package nvc.it.employeesql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import nvc.it.employeesql.model.Department;
import nvc.it.employeesql.model.Employee;
import nvc.it.employeesql.model.Project;
import nvc.it.employeesql.repository.DepartmentRepository;
import nvc.it.employeesql.repository.ProjectRepository;
import nvc.it.employeesql.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("")
    public ModelAndView employee(){
        List<Employee> employees = employeeService.findAll();
        return new ModelAndView("employee","employees",employees);
    }

    @GetMapping("/new")
    public String newEmployee (ModelMap modelMap) {
        Employee employee = new Employee();
        modelMap.addAttribute("employee", employee);
        return "newemployee";
    }

    @PostMapping("/add")
    public String saveEmployee(Employee employee, BindingResult result){
        if(result.hasErrors()){
            return "newemployee";
        }else{
            employeeService.save(employee);
            return "redirect:/employee";
        }
    }

    @GetMapping("/edit")
    public String editEmployee (){
        return "editemployee";
    }

    @GetMapping("/employee/name/{name}")
    public ModelAndView findEmployeeByName (@PathVariable("name") String name){
        List<Employee> employees = employeeService.findByName(name);
        return new ModelAndView("employee","employees",employees);
    }

    @PostMapping("/name") // การค้นหา Search
    public ModelAndView searchEmployeeByName(String name, ModelMap modelMap){
        List<Employee> employees = employeeService.findByName(name);
        modelMap.addAttribute("name", name);
        return new ModelAndView("employee", "employees", employees);
    }

    @GetMapping("/employee/salary/{salary}")
    public ModelAndView findEmployeeBySalary (@PathVariable("salary") Double salary){
        List<Employee> employees = employeeService.findBySalaryGreaterThanEqual(salary);
        return new ModelAndView("employee","employees",employees);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id){
        Employee employee = employeeService.getById(id);
        employeeService.delete(employee);
        return new ModelAndView("redirect:/employee");
    }

    @GetMapping("/edit/{id}") // การ Edit ข้อมูล
    public String editEmployee(@PathVariable int id, ModelMap modelMap){
        Employee employee = employeeService.getById(id);
        modelMap.addAttribute("employee", employee);
        return "editemployee";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute("employee") Employee employee, BindingResult result){
        if(result.hasErrors()){
            return "editemployee";
        }else{
            Employee emp = employeeService.getById(employee.getId());
            emp.setName(employee.getName());
            emp.setSalary(employee.getSalary());
            emp.setDepartment(employee.getDepartment());
            emp.setProject(employee.getProject());
            employeeService.save(emp);
            return "redirect:/employee";
        }
    }

    @ModelAttribute("departments")
    public List<Department> loaddepartments(){
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

    @ModelAttribute("projected")
    public List<Project> loadprojected(){
        List<Project> projected = projectRepository.findAll();
        return projected;
    }
}