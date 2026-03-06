package uidt.master.ecommerce.Authentification.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uidt.master.ecommerce.Authentification.enumeration.Role;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    @Column(unique = true, nullable = false)
    @Email(message = "Email should be valid")
    private String email;
    private String phone;
    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}