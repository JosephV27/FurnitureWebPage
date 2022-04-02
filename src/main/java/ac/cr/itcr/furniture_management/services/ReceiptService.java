package ac.cr.itcr.furniture_management.services;

import ac.cr.itcr.furniture_management.models.Receipt;
import ac.cr.itcr.furniture_management.models.ReceiptId;
import ac.cr.itcr.furniture_management.repositories.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    public List<Receipt> findAllReceipts() {
        return receiptRepository.findAll();
    }

    public void save(Receipt receipt) {
        receiptRepository.save(receipt);
    }

    public void deleteReceipt(ReceiptId id) {
        receiptRepository.deleteById(id);
    }

    public Receipt findReceiptById(ReceiptId id) {
        Optional<Receipt> receipt = receiptRepository.findById(id);
        return receipt.get();
    }


}
