package ac.cr.itcr.furniture_management.controllers;

import ac.cr.itcr.furniture_management.models.Employee;
import ac.cr.itcr.furniture_management.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String findAllEmployeesPage(Model model) {
        model.addAttribute("employees", employeeService.findAllEmployees());
        return "EmployeeView";
    }

    // Get the formatted date of the request
    @InitBinder
    public void initBinder (WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy/MM/dd"), true));
    }

    @GetMapping("/saveEmployeePage")
    public String saveEmployeePage(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "add_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee (@ModelAttribute("employee") Employee employee) {
        employee.createDateAdmission();
        employee.setStatus("activo");
        employeeService.save(employee);
        return "redirect:/";
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee (@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/";
    }

    @GetMapping("/updateEmployeePage/{id}")
    public String showUpdateEmployeePage(@PathVariable("id") int id, Model model) {
        Employee employee = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }

}
