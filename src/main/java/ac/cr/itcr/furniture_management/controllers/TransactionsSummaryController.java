package ac.cr.itcr.furniture_management.controllers;

import ac.cr.itcr.furniture_management.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransactionsSummaryController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/transactionsSummary")
    public String showTransactionsSummaryPage(Model model) {
        model.addAttribute("transactionsSummary", employeeService.getTransactionsSummary());
        return "TransactionsSummaryView";
    }

}
