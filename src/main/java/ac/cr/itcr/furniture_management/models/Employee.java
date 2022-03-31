package ac.cr.itcr.furniture_management.models;


import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "num_employee")
    @GeneratedValue(generator="seq_employee_id")
    @SequenceGenerator(name="seq_employee_id",sequenceName="seq_employee_id", allocationSize=1)
    private int numEmployee;
    private String identification;
    private String name;
    @Column(name = "first_lastname")
    private String firstLastName;
    @Column(name = "second_lastname")
    private String secondLastName;
    private int salary;
    private String status;
    @Column(name = "date_admission")
    private Date dateAdmission;

    public Employee(String identification, String name, String firstLastName, String secondLastName, int salary) {
        super();
        this.identification = identification;
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.salary = salary;
        this.status = "activo";
        this.dateAdmission = getDate();
    }

    public Employee() {

    }

    private Date getDate(){
        return new java.util.Date();
    }

    public int getNumEmployee() {
        return numEmployee;
    }

    public String getIdentification() {
        return identification;
    }

    public String getName() {
        return name;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public int getSalary() {
        return salary;
    }

    public String getStatus() {
        return status;
    }

    public Date getDateAdmission() {
        return dateAdmission;
    }

    public String getFormatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return simpleDateFormat.format(date);
    }

    public void setNumEmployee(int numEmployee) {
        this.numEmployee = numEmployee;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void createDateAdmission() {
        this.dateAdmission = new Date();
    }

    public void setDateAdmission(Date dateAdmission) {
        this.dateAdmission = dateAdmission;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "numEmployee=" + numEmployee +
                ", identification='" + identification + '\'' +
                ", name='" + name + '\'' +
                ", firstLastName='" + firstLastName + '\'' +
                ", secondLastName='" + secondLastName + '\'' +
                ", salary=" + salary +
                ", status='" + status + '\'' +
                ", dateAdmission=" + dateAdmission +
                '}';
    }
}
