package com.cognizant.productservice.service;

import com.cognizant.productservice.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product updateStock(Long id, Integer stock);
    void deleteProduct(Long id);
}
