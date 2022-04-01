package ac.cr.itcr.furniture_management.controllers;

import ac.cr.itcr.furniture_management.models.Product;
import ac.cr.itcr.furniture_management.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String findAllProductsPage(Model model) {
        model.addAttribute("products", productService.findAllProducts());
        return "ProductView";
    }

    @GetMapping("/saveProductPage")
    public String saveProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "add_product";
    }

    @PostMapping("/saveProduct")
    public String saveProduct (@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/";
    }

    @PostMapping("/updateProduct")
    public String updateProduct (@ModelAttribute("product") Product product) {
        System.out.println(product.toString());
        productService.save(product);
        return "redirect:/";
    }

    @GetMapping("/updateProductPage/{id}")
    public String showUpdateProductPage(@PathVariable("id") int id, Model model) {
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "update_product";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
    
}
