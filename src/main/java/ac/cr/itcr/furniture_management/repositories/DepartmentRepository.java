package ac.cr.itcr.furniture_management.repositories;

import ac.cr.itcr.furniture_management.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

    Optional<Department> findByName(String name);

}
