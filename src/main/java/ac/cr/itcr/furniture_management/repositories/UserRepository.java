package ac.cr.itcr.furniture_management.repositories;

import ac.cr.itcr.furniture_management.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}