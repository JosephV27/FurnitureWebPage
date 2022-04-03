package ac.cr.itcr.furniture_management.controllers;

import ac.cr.itcr.furniture_management.models.EmployeeDepartment;
import ac.cr.itcr.furniture_management.services.DepartmentService;
import ac.cr.itcr.furniture_management.services.EmployeeDepartmentService;
import ac.cr.itcr.furniture_management.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeDepartmentController {

    @Autowired
    private EmployeeDepartmentService employeeDepartmentService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employeeDepartment")
    public String findAllEmployeeDepartmentsPage(Model model) {
        List<EmployeeDepartment> employeeDepartments = employeeDepartmentService.findAllEmployeeDepartments();
        model.addAttribute("employeeDepartments", employeeDepartments);
        return "EmployeeDepartmentView";
    }

    @GetMapping("/saveEmployeeDepartmentPage")
    public String saveEmployeeDepartmentPage(Model model) {
        EmployeeDepartment employeeDepartment = new EmployeeDepartment();
        model.addAttribute("employeeDepartment", employeeDepartment);
        return "add_employee_department";
    }

    @PostMapping("/saveEmployeeDepartment")
    public String saveEmployeeDepartment(@ModelAttribute("employeeDepartment") EmployeeDepartment employeeDepartment) {
        int numEmployee = employeeService.findEmployeeByName(employeeDepartment.getEmployeeName()).getNumEmployee();
        int idDepartment = departmentService.findDepartmentByName(employeeDepartment.getDepartmentName()).getIdDepartment();
        employeeDepartment.id(numEmployee, idDepartment);
        employeeDepartmentService.save(employeeDepartment);
        return "redirect:/employeeDepartment";
    }


    @GetMapping("/deleteEmployeeDepartmentPage/{employee}/{department}")
    public String deleteEmployeeDepartment(@PathVariable("employee") int employee, @PathVariable("department") int department) {
        employeeDepartmentService.deleteEmployeeDepartment(employee, department);
        return "redirect:/employeeDepartment";
    }
    
}
