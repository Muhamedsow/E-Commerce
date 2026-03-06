package uidt.master.ecommerce.Authentification.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uidt.master.ecommerce.Authentification.entity.User;
import uidt.master.ecommerce.Authentification.enumeration.Role;
import uidt.master.ecommerce.Authentification.repository.UserRepository;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String save(User user) {
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