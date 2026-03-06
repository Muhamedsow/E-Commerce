package uidt.master.ecommerce.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uidt.master.ecommerce.catalogue.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}