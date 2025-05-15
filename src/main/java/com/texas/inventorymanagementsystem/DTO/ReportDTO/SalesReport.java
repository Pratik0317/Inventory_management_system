package com.texas.inventorymanagementsystem.DTO.ReportDTO;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class SalesReport {
    private LocalDateTime period;
    private String productName;
    private int quantitySold;
    private double totalRevenue;
    private double profit;
}
