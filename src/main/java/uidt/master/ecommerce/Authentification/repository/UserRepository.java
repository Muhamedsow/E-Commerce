package uidt.master.ecommerce.Authentification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uidt.master.ecommerce.Authentification.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}