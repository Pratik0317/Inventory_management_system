package com.texas.inventorymanagementsystem.service;

import com.texas.inventorymanagementsystem.model.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
    List<Category> getAllCategories();
    Category getCategoryById(Long id);

}
