package uidt.master.ecommerce.catalogue.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uidt.master.ecommerce.catalogue.entity.Product;
import uidt.master.ecommerce.catalogue.repository.ProductRepository;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final StoreService storeService;

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public boolean addProductToStore(Long storeId, Long productId) {
        var store = storeService.getStoreById(storeId).orElseThrow(() -> new RuntimeException("Store not found"));
        var product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        store.getProducts().add(product);
        storeService.saveStore(store);
    }
}