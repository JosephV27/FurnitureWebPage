package ac.cr.itcr.furniture_management.controllers;

import ac.cr.itcr.furniture_management.models.Employee;
import ac.cr.itcr.furniture_management.models.HistoricalTimeLogger;
import ac.cr.itcr.furniture_management.models.TimeLogger;
import ac.cr.itcr.furniture_management.services.EmployeeService;
import ac.cr.itcr.furniture_management.services.TimeLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TimeLoggerController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TimeLoggerService timeLoggerService;

    // Get the formatted date of the request
    @InitBinder
    public void initBinder (WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
    }

    @GetMapping("/timeLogger")
    public String timeLoggerPage(Model model) {
        Employee employee = new Employee();
        TimeLogger timeLogger = new TimeLogger();
        HistoricalTimeLogger historicalTimeLogger = new HistoricalTimeLogger();
        model.addAttribute("employee", employee);
        model.addAttribute("timeLogger", timeLogger);
        model.addAttribute("historicalTimeLogger", historicalTimeLogger);
        return "TimeLoggerView";
    }

    @GetMapping("/getEmployee")
    public String showUpdateEmployeePage(@RequestParam int numEmployee, Model model) {
        Employee employee = employeeService.findByNumEmployee(numEmployee);
        model.addAttribute("employee", employee);
        return "show_employee";
    }

    @PostMapping("/saveTimeLogger")
    public String saveTimeLogger (@ModelAttribute("timeLogger") TimeLogger timeLogger) {
        System.out.println(timeLogger.toString());
        String result = timeLoggerService.save(timeLogger);
        System.out.println(result);
        return "redirect:/timeLogger";
    }

    @GetMapping("/HistoricalTimeLogger")
    @Transactional
    public String showUpdateEmployeeHoursWorked(@RequestParam Date initialDate, @RequestParam Date endingDate, Model model) {
        System.out.println(initialDate);
        List<TimeLogger> timeLoggers = timeLoggerService.findbyDates(initialDate, endingDate);
        for (TimeLogger timeLogger: timeLoggers){
            System.out.println(timeLogger.toString());
        }
        model.addAttribute("timeLoggers", timeLoggers);
        return "show_hours_worked";
    }

//    @GetMapping("/deleteTimeLogger/{numEmployee}/{timeLoggerDate}")
    @GetMapping("/deleteTimeLogger/{numEmployee}/{dateTimeLogger}")
    public String deleteTimeLogger(@PathVariable("numEmployee") int numEmployee, @PathVariable("dateTimeLogger") String dateTimeLogger) throws ParseException {
        timeLoggerService.deleteTimeLogger(numEmployee, dateTimeLogger);
        return "redirect:/timeLogger";
    }

}
