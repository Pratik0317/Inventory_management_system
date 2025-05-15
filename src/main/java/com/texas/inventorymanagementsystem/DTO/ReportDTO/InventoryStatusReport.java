package com.texas.inventorymanagementsystem.DTO.ReportDTO;

import lombok.Data;

@Data
public class InventoryStatusReport {

    private String productName;
    private String sku;
    private String category;
    private int currentStock;
    private double price;
    private String status;
    private String supplier;
}
