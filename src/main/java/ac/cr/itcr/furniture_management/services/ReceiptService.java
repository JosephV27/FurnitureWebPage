package ac.cr.itcr.furniture_management.services;

import ac.cr.itcr.furniture_management.models.Receipt;
import ac.cr.itcr.furniture_management.models.ReceiptId;
import ac.cr.itcr.furniture_management.repositories.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    public List<Receipt> findAllReceipts() {

        List<Object[]> elements = receiptRepository.findAll();
        List<Receipt> receipts = new ArrayList<>(elements.size());
        for (Object[] row : elements) {
            receipts.add(new Receipt(new ReceiptId(((BigDecimal) row[0]).intValue(),
                    ((BigDecimal) row[1]).intValue()),
                    (String) row[2],
                    (String) row[3]));
        }
       return receipts;
    }

    public void save(Receipt receipt) {
        receiptRepository.save(receipt);
    }

    public void deleteReceipt(int product, int customer) {
        receiptRepository.deleteById(product, customer);
    }


}
