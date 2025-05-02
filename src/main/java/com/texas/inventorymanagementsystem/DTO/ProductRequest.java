package com.texas.inventorymanagementsystem.DTO;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private String sku;
    private double price;
    private int quantity;
    private Long categoryId;
    private Long supplierId;
}
