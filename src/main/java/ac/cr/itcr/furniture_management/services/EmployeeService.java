package ac.cr.itcr.furniture_management.services;

import ac.cr.itcr.furniture_management.models.Employee;
import ac.cr.itcr.furniture_management.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee(int id){
        employeeRepository.deleteById(id);
    }

    public Employee findEmployeeById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.get();
    }
    public Employee findEmployeeByName(String name){
        Optional<Employee> employee = employeeRepository.findByName(name);
        return employee.get();

    }

}
