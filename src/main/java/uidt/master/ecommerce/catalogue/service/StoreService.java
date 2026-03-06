package uidt.master.ecommerce.catalogue.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uidt.master.ecommerce.catalogue.entity.Store;
import uidt.master.ecommerce.catalogue.repository.StoreRepository;

@Service
@AllArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public void addStore(Store store) {
        storeRepository.save(store);
    }

    public Store getStoreById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }

    public void deleteStoreById(Long id) {
        storeRepository.deleteById(id);
    }
}