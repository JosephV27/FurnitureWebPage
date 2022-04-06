package ac.cr.itcr.furniture_management.services;

import ac.cr.itcr.furniture_management.models.Employee;
import ac.cr.itcr.furniture_management.models.TransactionsSummary;
import ac.cr.itcr.furniture_management.repositories.EmployeeRepository;
import ac.cr.itcr.furniture_management.repositories.TimeLoggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private static final String getEmployeeInfo = "SELECT consult_time_logger.get_employee_info(:num_employee) FROM dual";

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TimeLoggerRepository timeLoggerRepository;

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

    public Employee findEmployeeByIdentification(String identification){
        Optional<Employee> employee = employeeRepository.findByIdentification(identification);
        return employee.get();

    }

    public Employee findByNumEmployee(int numEmployee){

        String employeeString = (String)  em.createNativeQuery(getEmployeeInfo)
                .setParameter("num_employee", numEmployee)
                .getSingleResult();

        String[] employeeParts = employeeString.split(" ");

        return new Employee(Integer.parseInt(employeeParts[0]),
                employeeParts[1], employeeParts[2],
                employeeParts[3],employeeParts[4],
                Integer.parseInt(employeeParts[5]), employeeParts[6]);
    }

    public TransactionsSummary getTransactionsSummary() {

        return new TransactionsSummary(employeeRepository.getActiveEmployees(), employeeRepository.getInactiveEmployees(),
                employeeRepository.getSalariesByMonth(), employeeRepository.getSalariesByYear(), timeLoggerRepository.getTotalHoursWorkedByActualMonth());

    }

}
