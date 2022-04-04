package ac.cr.itcr.furniture_management.models;

import javax.persistence.*;

@Entity
@Table(name = "employee_department")
public class EmployeeDepartment {

    @EmbeddedId
    private EmployeeDepartmentId id;
    @Transient
    private String employeeIdentification;
    @Transient
    private String nameEmployee;
    @Transient
    private String firstLastName;
    @Transient
    private String secondLastName;
    @Transient
    private String departmentName;

    public EmployeeDepartment(EmployeeDepartmentId id, String employeeIdentification, String nameEmployee, String firstLastName, String secondLastName, String departmentName) {
        super();
        this.id = id;
        this.employeeIdentification = employeeIdentification;
        this.nameEmployee = nameEmployee;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.departmentName = departmentName;
    }

    public EmployeeDepartment() {

    }


    public EmployeeDepartmentId getId() {
        return id;
    }

    public void setId(EmployeeDepartmentId id) {
        this.id = id;
    }

    public String getEmployeeIdentification() {
        return employeeIdentification;
    }

    public void setEmployeeIdentification(String employeeIdentification) {
        this.employeeIdentification = employeeIdentification;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void id(int numEmployee, int idDepartment) {
        this.id = new EmployeeDepartmentId(numEmployee, idDepartment);
    }
}
