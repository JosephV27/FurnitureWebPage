package ac.cr.itcr.furniture_management.repositories;

import ac.cr.itcr.furniture_management.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByIdentification(String identification);

    @Query(value = "SELECT COUNT(*) FROM employee WHERE status = 'activo'", nativeQuery = true)
    int getActiveEmployees();

    @Query(value = "SELECT COUNT(*) FROM employee WHERE status = 'inactivo'", nativeQuery = true)
    int getInactiveEmployees();

    @Query(value = "SELECT SUM(salary) FROM employee WHERE status = 'activo'", nativeQuery = true)
    int getSalariesByMonth();

    @Query(value = "SELECT SUM(salary) * 12 FROM employee WHERE status = 'activo'", nativeQuery = true)
    int getSalariesByYear();

}
