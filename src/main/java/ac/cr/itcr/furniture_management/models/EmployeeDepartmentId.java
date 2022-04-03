package ac.cr.itcr.furniture_management.models;


import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EmployeeDepartmentId implements Serializable {
    private int numEmployee;
    private int idDepartment;

    public EmployeeDepartmentId(int numEmployee, int idDepartment) {
        this.numEmployee = numEmployee;
        this.idDepartment = idDepartment;
    }

    public EmployeeDepartmentId(){

    }

    public int getNumEmployee() {
        return numEmployee;
    }

    public void setNumEmployee(int numEmployee) {
        this.numEmployee = numEmployee;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    @Override
    public String toString() {
        return "ReceiptId{" +
                "numEmployee=" + numEmployee +
                ", idDepartment=" + idDepartment +
                '}';
    }
}
