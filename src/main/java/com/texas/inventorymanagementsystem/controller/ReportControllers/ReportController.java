package com.texas.inventorymanagementsystem.controller.ReportControllers;

import com.texas.inventorymanagementsystem.DTO.ReportDTO.CategoryAnalysisReport;
import com.texas.inventorymanagementsystem.DTO.ReportDTO.InventoryStatusReport;
import com.texas.inventorymanagementsystem.DTO.ReportDTO.SalesReport;
import com.texas.inventorymanagementsystem.service.ReportService.CategoryReportService;
import com.texas.inventorymanagementsystem.service.ReportService.InventoryReportService;
import com.texas.inventorymanagementsystem.service.ReportService.SalesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private InventoryReportService inventoryReportService;

    @Autowired
    private SalesReportService salesReportService;

    @Autowired
    private CategoryReportService categoryReportService;

    @GetMapping("/inventory-status")
    public ResponseEntity<List<InventoryStatusReport>> getInventoryStatus() {
        return ResponseEntity.ok(inventoryReportService.generateInventoryStatusReport());
    }

    @GetMapping("/sales")
    public ResponseEntity<List<SalesReport>> getSalesReport(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime start,
                                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime end) {
        return ResponseEntity.ok(salesReportService.generateSalesReport(start, end));

    }

    @GetMapping("/category-analysis")
    public ResponseEntity<List<CategoryAnalysisReport>> getCategoryAnalysis() {
        return ResponseEntity.ok(categoryReportService.generateCategoryAnalysisReport());
    }
}
