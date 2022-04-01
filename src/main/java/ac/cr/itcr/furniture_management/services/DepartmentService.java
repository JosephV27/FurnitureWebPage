package ac.cr.itcr.furniture_management.services;

import ac.cr.itcr.furniture_management.models.Department;
import ac.cr.itcr.furniture_management.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }

    public void save(Department department) {
        departmentRepository.save(department);
    }

    public void deleteDepartment(int id){
        departmentRepository.deleteById(id);
    }

    public Department findDepartmentById(int id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department.get();
    }
}