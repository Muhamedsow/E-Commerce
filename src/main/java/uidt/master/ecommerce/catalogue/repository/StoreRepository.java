package uidt.master.ecommerce.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uidt.master.ecommerce.catalogue.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {}
