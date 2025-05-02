package com.texas.inventorymanagementsystem.serviceimpl;

import com.texas.inventorymanagementsystem.model.Category;
import com.texas.inventorymanagementsystem.repository.CategoryRepo;
import com.texas.inventorymanagementsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Category addCategory(Category category) {
        if(categoryRepo.findCategoryByName(category.getName()).isPresent()){
            throw new RuntimeException("Category already exists");
        }
        return categoryRepo.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
       Category existingCategory = categoryRepo.findById(id).orElseThrow(() ->
               new ResponseStatusException(HttpStatus.NOT_FOUND,"Category with id " + id + " not found"));
       if(!existingCategory.getName().equals(category.getName())){
           if(categoryRepo.existsByName(category.getName())){
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Category with name "
                       + category.getName() + " already exists");
           }
           existingCategory.setName(category.getName());
           existingCategory.setDescription(category.getDescription());
       }
       return categoryRepo.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long id) {
      Category category = categoryRepo.findById(id).orElseThrow(()->
              new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found with id: "+id));
      categoryRepo.delete(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found with id: "+id));
    }
}
