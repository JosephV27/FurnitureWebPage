package ac.cr.itcr.furniture_management.controllers;

import ac.cr.itcr.furniture_management.models.Customer;
import ac.cr.itcr.furniture_management.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String findAllCustomersPage(Model model) {
        model.addAttribute("customers", customerService.findAllCustomers());
        return "CustomerView";
    }
    
    @GetMapping("/saveCustomerPage")
    public String saveCustomerPage(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "add_customer";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer (@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        return "redirect:/";
    }

    @PostMapping("/updateCustomer")
    public String updateCustomer (@ModelAttribute("customer") Customer customer) {
        System.out.println(customer.toString());
        customerService.save(customer);
        return "redirect:/";
    }

    @GetMapping("/updateCustomerPage/{id}")
    public String showUpdateCustomerPage(@PathVariable("id") int id, Model model) {
        Customer customer = customerService.findCustomerById(id);
        model.addAttribute("customer", customer);
        return "update_customer";
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable("id") int id) {
        customerService.deleteCustomer(id);
        return "redirect:/";
    }

}
