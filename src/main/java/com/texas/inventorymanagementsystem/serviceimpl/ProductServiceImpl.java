package com.texas.inventorymanagementsystem.serviceimpl;

import com.texas.inventorymanagementsystem.DTO.ProductRequest;
import com.texas.inventorymanagementsystem.model.Category;
import com.texas.inventorymanagementsystem.model.Product;
import com.texas.inventorymanagementsystem.model.Supplier;
import com.texas.inventorymanagementsystem.repository.CategoryRepo;
import com.texas.inventorymanagementsystem.repository.ProductRepo;
import com.texas.inventorymanagementsystem.repository.SupplierRepo;
import com.texas.inventorymanagementsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Product addProduct(ProductRequest request) {
        Supplier supplier = supplierRepo.findById(request.getSupplierId()).orElseThrow(() ->
    new RuntimeException("Supplier not found"));

        Category category = categoryRepo.findById(request.getCategoryId()).orElseThrow(() ->
                new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategory(category);
        product.setPrice(request.getPrice());
        product.setSku(request.getSku());
        product.setQuantity(request.getQuantity());
        product.setSupplier(supplier);
        return productRepo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: "+id));
    }

    @Override
    public Product updateProduct(Long id, ProductRequest productDetails) {
        Product product = productRepo.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: "+id));

        Supplier supplier = supplierRepo.findById(productDetails.getSupplierId()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found with id: "+productDetails.getSupplierId()+"not found"));

        Category category = categoryRepo.findById(productDetails.getCategoryId()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with id: "+productDetails.getCategoryId()+" not found"));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());
        product.setSupplier(supplier);
        product.setCategory(category);
        product.setSku(productDetails.getSku());
        return productRepo.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepo.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found with id: "+id));
        productRepo.delete(product);
    }
}