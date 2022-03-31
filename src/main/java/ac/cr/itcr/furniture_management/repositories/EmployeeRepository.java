package ac.cr.itcr.furniture_management.repositories;

import ac.cr.itcr.furniture_management.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
