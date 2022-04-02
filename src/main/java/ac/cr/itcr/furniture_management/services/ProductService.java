package ac.cr.itcr.furniture_management.services;

import ac.cr.itcr.furniture_management.models.Product;
import ac.cr.itcr.furniture_management.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }

    public Product findProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        return product.get();
    }

    public  Product findProductByName(String productName) {
        Optional<Product> product = productRepository.findByName(productName);
        return product.get();
    }


}
