package ac.cr.itcr.furniture_management.repositories;

import ac.cr.itcr.furniture_management.models.Receipt;
import ac.cr.itcr.furniture_management.models.ReceiptId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface ReceiptRepository extends JpaRepository<Receipt, ReceiptId> {


    //TODO see if the query binds the product name and customer identification strings

    @Query(value = "SELECT r.id_customer,r.id_product,c.identification, p.name " +
            "FROM product p" +
            "INNER JOIN receipt r" +
            "ON r.id_product = p.id_product" +
            "INNER JOIN customer c" +
            "ON r.id_customer = c.id_customer;",
            nativeQuery = true)
    List<Receipt> findAll();

}

