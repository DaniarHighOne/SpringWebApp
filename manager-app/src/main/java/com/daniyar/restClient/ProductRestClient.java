package com.daniyar.restClient;

import com.daniyar.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRestClient {
    //method for getting list of products
    List<Product> findAllProducts();
    //void create product
    Product createProduct(String title,String details);
    //void get single product
    Optional<Product> findProduct(int productId);
    //void update product
    void updateProduct(int productId, String title, String details);
    //void delete product
    void deleteProduct(int productId);
}
