package uidt.master.ecommerce.catalogue.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uidt.master.ecommerce.Authentification.entity.User;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String address;
    private String phoneNumber;
    @Column(unique = true, nullable = false)
    @Email(message = "Email should be valid")
    private String email;
    private String websiteUrl;
    @ManyToOne
    private User owner;
//    private String logoUrl;
}