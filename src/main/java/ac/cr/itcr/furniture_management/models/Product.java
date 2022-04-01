package ac.cr.itcr.furniture_management.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class Product {

    @Id
    @Column(name = "id_product")
    @GeneratedValue(generator="seq_product_id")
    @SequenceGenerator(name="seq_product_id",sequenceName="seq_product_id", allocationSize=1)
    private int idProduct;

    //TODO FOREIGN KEYS
    @Column(name = "id_category")
    private int idCategory;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private float price;

    public Product()
    {

    }

    public Product(int idProduct, int idCategory, String name, String description, float price) {
        this.idProduct = idProduct;
        this.idCategory = idCategory;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", idCategory=" + idCategory +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }


}
