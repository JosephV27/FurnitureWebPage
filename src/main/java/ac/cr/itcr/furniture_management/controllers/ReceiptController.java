package ac.cr.itcr.furniture_management.controllers;

import ac.cr.itcr.furniture_management.models.Receipt;
import ac.cr.itcr.furniture_management.services.CustomerService;
import ac.cr.itcr.furniture_management.services.ProductService;
import ac.cr.itcr.furniture_management.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class ReceiptController {
    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/receipt")
    public String findAllReceiptsPage(Model model) {
        List<Receipt> receipts = receiptService.findAllReceipts();
        model.addAttribute("receipts", receipts);
        return "ReceiptView";
    }

    @GetMapping("/saveReceiptPage")
    public String saveReceiptPage(Model model) {
        Receipt receipt = new Receipt();
        model.addAttribute("receipt", receipt);
        return "add_receipt";
    }

    @PostMapping("/saveReceipt")
    public String saveReceipt(@ModelAttribute("receipt") Receipt receipt) {
        int idProduct = productService.findProductByName(receipt.getProductName()).getIdProduct();
        int idCustomer = customerService.findCustomerByIdentification(receipt.getCustomerIdentification()).getIdCustomer();
        receipt.id(idCustomer, idProduct);
        receiptService.save(receipt);
        return "redirect:/receipt";
    }


    @GetMapping("/deleteReceiptPage/{product}/{customer}")
    public String deleteReceipt(@PathVariable("product") int product, @PathVariable("customer") int customer) {
        receiptService.deleteReceipt(product, customer);
        return "redirect:/receipt";
    }

}
