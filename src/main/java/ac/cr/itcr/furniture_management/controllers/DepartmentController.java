package ac.cr.itcr.furniture_management.controllers;

import ac.cr.itcr.furniture_management.models.Department;
import ac.cr.itcr.furniture_management.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/department")
    public String findAllDepartmentsPage(Model model) {
        model.addAttribute("departments", departmentService.findAllDepartments());
        return "DepartmentView";
    }

    @GetMapping("/saveDepartmentPage")
    public String saveDepartmentPage(Model model) {
        Department department = new Department();
        model.addAttribute("department", department);
        return "add_department";
    }

    @PostMapping("/saveDepartment")
    public String saveDepartment (@ModelAttribute("department") Department department) {
        departmentService.save(department);
        return "redirect:/department";
    }


    @GetMapping("/updateDepartmentPage/{id}")
    public String showUpdateDepartmentPage(@PathVariable("id") int id, Model model) {
        Department department = departmentService.findDepartmentById(id);
        model.addAttribute("department", department);
        return "update_department";
    }

    @GetMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable("id") int id) {
        departmentService.deleteDepartment(id);
        return "redirect:/department";
    }

}
