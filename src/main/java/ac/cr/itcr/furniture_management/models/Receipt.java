package ac.cr.itcr.furniture_management.models;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;


@Entity
@Table(name = "receipt")
public class Receipt implements Serializable {

    @EmbeddedId
    private ReceiptId id;
    @Transient
    private String productName;
    @Transient
    private String customerIdentification;

    public Receipt(String customerIdentification, String productName) {
        super();
        this.productName = productName;
        this.customerIdentification = customerIdentification;
    }

    public Receipt() {

    }

    public void id(int idCustomer, int idProduct) {
        this.id = new ReceiptId(idCustomer, idProduct);
    }


    public ReceiptId getId() {
        return id;
    }

    public void setId(ReceiptId id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerIdentification() {
        return customerIdentification;
    }

    public void setCustomerIdentification(String customerIdentification) {
        this.customerIdentification = customerIdentification;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", customerIdentification='" + customerIdentification + '\'' +
                '}';
    }
}