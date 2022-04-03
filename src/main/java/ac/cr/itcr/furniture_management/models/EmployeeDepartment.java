package ac.cr.itcr.furniture_management.models;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class EmployeeDepartment {

    @EmbeddedId
    private EmployeeDepartmentId id;
    @Transient
    private String employeeName;
    @Transient
    private String departmentName;

    public EmployeeDepartment(EmployeeDepartmentId id, String employeeName, String departmentName) {
        super();
        this.id = id;
        this.employeeName = employeeName;
        this.departmentName = departmentName;
    }

    public EmployeeDepartment(){

    }

    public void id(int numEmployee, int idDepartment){
        this.id = new EmployeeDepartmentId(numEmployee,idDepartment);
    }

    public EmployeeDepartmentId getId() {
        return id;
    }

    public void setId(EmployeeDepartmentId id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "EmployeeDepartment{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
