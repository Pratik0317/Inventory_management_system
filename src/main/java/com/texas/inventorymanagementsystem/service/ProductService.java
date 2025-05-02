package com.texas.inventorymanagementsystem.service;

import com.texas.inventorymanagementsystem.DTO.ProductRequest;
import com.texas.inventorymanagementsystem.model.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(ProductRequest request);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product updateProduct(Long id, Product productDetails);
    void deleteProduct(Long id);
}
