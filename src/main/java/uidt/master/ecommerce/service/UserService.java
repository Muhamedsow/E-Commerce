package uidt.master.ecommerce.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uidt.master.ecommerce.entity.User;
import uidt.master.ecommerce.enumeration.Role;
import uidt.master.ecommerce.repository.UserRepository;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String save(User user) {
        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return "Invalid email format";
        }
        User existingUser = findByEmail(user.getEmail());
        if (existingUser != null) {
            return "Email already in use";
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.valueOf(user.getRole().name()));
        this.userRepository.save(user);
        return "User created successfully!";
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }
}