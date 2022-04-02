package ac.cr.itcr.furniture_management.models;


import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ReceiptId implements Serializable {
    private int idCustomer;
    private int idProduct;

    public ReceiptId(int idCustomer, int idProduct) {
        this.idCustomer = idCustomer;
        this.idProduct = idProduct;
    }

    public ReceiptId(){

    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public String toString() {
        return "ReceiptId{" +
                "idCustomer=" + idCustomer +
                ", idProduct=" + idProduct +
                '}';
    }
}
