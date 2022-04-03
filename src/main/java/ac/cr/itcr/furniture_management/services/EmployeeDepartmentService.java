package ac.cr.itcr.furniture_management.services;

import ac.cr.itcr.furniture_management.models.EmployeeDepartment;
import ac.cr.itcr.furniture_management.models.EmployeeDepartmentId;
import ac.cr.itcr.furniture_management.repositories.EmployeeDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeDepartmentService {

    @Autowired
    private EmployeeDepartmentRepository employeeDepartmentRepository;

    public List<EmployeeDepartment> findAllEmployeeDepartments() {

        List<Object[]> elements = employeeDepartmentRepository.findAll();
        List<EmployeeDepartment> employeeDepartments = new ArrayList<>(elements.size());
        for (Object[] row : elements) {
            employeeDepartments.add(new EmployeeDepartment(new EmployeeDepartmentId(((BigDecimal) row[0]).intValue(),
                    ((BigDecimal) row[1]).intValue()),
                    (String) row[2],
                    (String) row[3]));
        }
        return employeeDepartments;
    }

    public void save(EmployeeDepartment employeeDepartment) {
        employeeDepartmentRepository.save(employeeDepartment);
    }

    public void deleteEmployeeDepartment(int product, int customer) {
        employeeDepartmentRepository.deleteById(product, customer);
    }


}
