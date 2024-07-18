package ag.selm.catalogue.service;


import ag.selm.catalogue.entity.Product;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public interface ProductService {


    List<Product> findAllProducts();

    Product createProduct(String title, String details);

    Optional<Product> findProduct(int productId);

    void updateProduct(Integer id, String title, String details);

    void deleteProduct(Integer id);
}
