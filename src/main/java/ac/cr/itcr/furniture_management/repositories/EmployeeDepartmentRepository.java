package ac.cr.itcr.furniture_management.repositories;

import ac.cr.itcr.furniture_management.models.EmployeeDepartment;
import ac.cr.itcr.furniture_management.models.EmployeeDepartmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface EmployeeDepartmentRepository extends JpaRepository<EmployeeDepartment, EmployeeDepartmentId> {


    @Query(value = "SELECT ed.num_employee, ed.id_department, e.name, d.name " +
            "FROM employee e " +
            "INNER JOIN employee_department ed " +
            "ON ed.num_employee = e.num_employee " +
            "INNER JOIN department d" +
            "ON ed.id_department = d.id_department",
            nativeQuery = true)
    List findAll();


    @Modifying
    @Transactional
    @Query(value = "delete from employee_department where num_employee = ?1 and id_department = ?2", nativeQuery = true)
    void deleteById(int employee, int department);

}

