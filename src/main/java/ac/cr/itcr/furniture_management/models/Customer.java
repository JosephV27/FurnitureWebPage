package ac.cr.itcr.furniture_management.models;


import javax.persistence.*;


@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "id_customer")
    @GeneratedValue(generator = "seq_customer_id")
    @SequenceGenerator(name = "seq_customer_id", sequenceName = "seq_customer_id", allocationSize = 1)
    private int idCustomer;
    private String identification;
    private String name;
    private String lastname;
    private String address;
    private String phone;

    public Customer(String identification, String name, String lastname, String address, String phone) {
        super();
        this.identification = identification;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
    }

    public Customer() {

    }


    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "idCustomer=" + idCustomer +
                ", identification='" + identification + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
