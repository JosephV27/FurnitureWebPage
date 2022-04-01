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
    @Column(name = "identification")
    private String identification;
    @Column(name = "name")
    private String name;
    //TODO Remove unnecessary attributes
    @Column(name = "first_lastname")
    private String firstLastName;
    @Column(name = "second_lastname")
    private String secondLastName;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "phone")
    private String phone;


    public Customer(int idCustomer, String identification, String name,
                    String firstLastName, String secondLastName,
                    String address, String city, String phone) {
        this.idCustomer = idCustomer;
        this.identification = identification;
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.address = address;
        this.city = city;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
                ", firstLastName='" + firstLastName + '\'' +
                ", secondLastName='" + secondLastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }


}
