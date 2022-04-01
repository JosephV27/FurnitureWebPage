package ac.cr.itcr.furniture_management.models;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @Column(name = "id_department")
    @GeneratedValue(generator="seq_department_id")
    @SequenceGenerator(name="seq_department_id",sequenceName="seq_department_id", allocationSize=1)
    private int idDepartment;
    @Column(name = "name")
    private String name;


    public Department(int idDepartment, String name) {
        this.idDepartment = idDepartment;
        this.name = name;
    }

    public Department(){

    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "idDepartment=" + idDepartment +
                ", name='" + name + '\'' +
                '}';
    }


}
