package com.texas.inventorymanagementsystem.repository;

import com.texas.inventorymanagementsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    Optional<Category> findCategoryByName(String name);
    boolean existsByName(String name);
}
