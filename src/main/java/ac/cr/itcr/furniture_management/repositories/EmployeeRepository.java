package ac.cr.itcr.furniture_management.repositories;

import ac.cr.itcr.furniture_management.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByIdentification(String identification);

}
