package ac.cr.itcr.furniture_management.repositories;

import ac.cr.itcr.furniture_management.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByName(String productName);
}

