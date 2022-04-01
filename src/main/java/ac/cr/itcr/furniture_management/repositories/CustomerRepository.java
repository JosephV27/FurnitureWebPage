package ac.cr.itcr.furniture_management.repositories;

import ac.cr.itcr.furniture_management.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
