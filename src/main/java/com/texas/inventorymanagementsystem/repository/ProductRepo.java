package com.texas.inventorymanagementsystem.repository;

import com.texas.inventorymanagementsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
   Optional<Product>findProductByName(String name);

}
