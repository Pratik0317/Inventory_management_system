package com.texas.inventorymanagementsystem.DTO.ReportDTO;

import lombok.Data;

@Data
public class CategoryAnalysisReport {
    private String categoryName;
    private int productCount;
    private int totalStock;
    private double totalValue;
}
