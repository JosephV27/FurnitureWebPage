package ac.cr.itcr.furniture_management.services;

import ac.cr.itcr.furniture_management.models.Customer;
import ac.cr.itcr.furniture_management.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }

    public Customer findCustomerById(int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.get();
    }

}
