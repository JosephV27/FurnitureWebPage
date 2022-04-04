package ac.cr.itcr.furniture_management.controllers;

import ac.cr.itcr.furniture_management.services.DepartmentService;
import ac.cr.itcr.furniture_management.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TimeLoggerController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/timeLogger")
    public String findHoursWorked(Model model) {
        model.addAttribute("department", departmentService.findAllDepartments());
        return "TimeLoggerView";
    }
}
