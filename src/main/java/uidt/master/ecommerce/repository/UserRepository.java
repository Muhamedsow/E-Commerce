package uidt.master.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uidt.master.ecommerce.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}