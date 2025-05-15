package com.texas.inventorymanagementsystem.DTO.ReportDTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SupplierPerformanceReport {
    private String supplierName;
    private int productsSupplied;
    private int totalOrders;
    private LocalDateTime lastOrderDate;
    private double totalSpend;
}
