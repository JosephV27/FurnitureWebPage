package ac.cr.itcr.furniture_management.repositories;

import ac.cr.itcr.furniture_management.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{
}
